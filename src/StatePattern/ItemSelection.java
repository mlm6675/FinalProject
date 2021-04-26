package StatePattern;
import AdapterPattern.Display;
import FactoryMethodPattern.Item;
import FactoryMethodPattern.Items.Null_Item;
import Sources.*;

import javax.swing.*;

public class ItemSelection extends State{
    private boolean isSelectionValid;

    public ItemSelection(){
        isSelectionValid = false;
    }

    @Override
    public State processEvent(int event) {
        switch(event)
        {
            case moneyEnteredEvent:
                boolean isRealMoney = validateMoney();
                if(isRealMoney)
                    vendingMachine.setBalance(vendingMachine.getBalance() + vendingMachine.getCurrentDeposit());
                else
                    JOptionPane.showMessageDialog(null, "The money you've entered is fake.");
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
                    JOptionPane.showMessageDialog(null, "This item is unavailable.");
                    StringBuilder msg = new StringBuilder();
                    msg.append("Please, select an item.\n");
                    msg.append("Your selection > ");
                    vendingMachine.getDisplay().setDispalyText(msg.toString());
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
        Display display = vendingMachine.getDisplay();
        String text = display.getDisplayText();
        text = text.substring(text.indexOf('>') + 1);
        text = text.trim();
        if(text.isBlank())
            return new Null_Item();
        int index = Integer.parseInt(text);
        return vendingMachine.getInventory().getItemNumber(index);
    }

    @Override
    public State processEvent(int event, int key) {
        Display screen = vendingMachine.getDisplay();
        if(event == State.digitPressEvent){
            screen.setDispalyText(screen.getDisplayText() + key);
        }
        if(event == State.programmableButtonPressEvent){
            screen.setDispalyText("Running external program #" + key);
            vendingMachine.runProgram(key);
            enter();
        }
        return nextState(event);
    }

    @Override
    protected State nextState(int event) {
        State current = vendingMachine.getCurrentState();
        switch(event)
        {
            case cancelPressEvent:
                current.leave();
                State.Idle.enter();
                return State.Idle;
            case filterPressEvent:
                current.leave();
                State.ItemFiltering.enter();
                return State.ItemFiltering;
            case confirmPressEvent:
                if(isSelectionValid){
                    current.leave();
                    State.SelectionValidation.enter();
                    return State.SelectionValidation;
                }else{
                    return this;
                }
            default: //For all other events, you stay in this state
                return this;
        }
    }

    @Override
    protected void enter()
    {
        super.enter();
        Display display = vendingMachine.getDisplay();
        StringBuilder msg = new StringBuilder();
        msg.append("Please, select an item.\n");
        msg.append("Your selection > ");
        display.setDispalyText(msg.toString());

        Inventory inv =vendingMachine.getInventory();
        JButton[] buttons = display.getButtons();
        for (int i = 0, size = inv.getItemCount(); i!= size; i++) {
            buttons[i].setEnabled(inv.getMask(inv.getItemNumber(i+1)));
        }
    }

    @Override
    protected void leave() {
        super.leave();
        isSelectionValid = false;

        JButton[] buttons = vendingMachine.getDisplay().getButtons();
        for (JButton b : buttons) {
            b.setEnabled(true);
        }
    }
}
