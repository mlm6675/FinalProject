package StatePattern;
import FactoryMethodPattern.Item;
import FactoryMethodPattern.Items.Null_Item;
import Sources.*;

public class ItemSelection extends State{
    private boolean isSelectionValid;

    @Override
    public State processEvent(int event) {
        switch(event)
        {
            case moneyEnteredEvent:
                boolean isRealMoney = validateMoney();
                if(isRealMoney)
                    vendingMachine.setBalance(vendingMachine.getBalance() + vendingMachine.getCurrentDeposit());
                vendingMachine.setCurrentDeposit(0);
                break;
            case cancelPressEvent:
                vendingMachine.refund();
                break;
            case confirmPressEvent:
                Item selectedItem = getItemFromInventory();
                isSelectionValid = isValid(selectedItem);
                if(isSelectionValid){
                    vendingMachine.setSelectedItem(selectedItem);
                }else{
                    int lenght = vendingMachine.getDisplay().length();
                    vendingMachine.getDisplay().delete(0, lenght);
                }
                break;
        }
        return nextState(event);
    }

    private boolean isValid(Item selectedItem) {
        if(selectedItem instanceof Null_Item)
            return false;
        Inventory inv = vendingMachine.getInventory();
        int quantity = inv.getItemQuantity(selectedItem);
        boolean mask = inv.getMask(selectedItem);
        double balance = vendingMachine.getBalance() - selectedItem.getPrice();
        return quantity>0 && mask && balance >= 0;
    }

    private Item getItemFromInventory() {
        if(vendingMachine.getDisplay().length()==0)
            return new Null_Item();
        int index = Integer.parseInt(vendingMachine.getDisplay().toString());
        return vendingMachine.getInventory().getItemNumber(index);
    }

    @Override
    public State processEvent(int event, int key) {
        if(event == State.digitPressEvent){
            StringBuilder display = vendingMachine.getDisplay();
            display.append(key);
        }
        return nextState(event);
    }

    @Override
    protected State nextState(int event) {
        switch(event)
        {
            case cancelPressEvent:
                return super.Idle;
            case filterPressEvent:
                return super.ItemFiltering;
            case confirmPressEvent:
                return (isSelectionValid)?super.SelectionValidation:this;
            default: //For all other events, you stay in this state
                return this;
        }
    }

    protected void enter()
    {
        System.out.println("ENTERED: ItemSelection");
        System.out.println("Please make a selection.");
        //note: this is going to have to go through the gui and NOT through the console
    }

    @Override
    protected void leave() {
        isSelectionValid = false;
    }
}
