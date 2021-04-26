package StatePattern;
import FactoryMethodPattern.Items.Null_Item;
import Sources.*;

public class SelectionValidation extends State{

    @Override
    public State processEvent(int event) {
        switch(event) {
            case cancelPressEvent:
                vendingMachine.setSelectedItem(new Null_Item());
                break;
            case confirmPressEvent:
                double newBalance = vendingMachine.getBalance() - vendingMachine.getSelectedItem().getPrice();
                vendingMachine.setBalance(newBalance);
                vendingMachine.dispense();
                vendingMachine.refund();
                break;
        }
        return nextState(event);
    }

    @Override
    public State processEvent(int event, int key) {
        return nextState(event);
    }

    @Override
    protected State nextState(int event) {
        switch(event)
        {
            case confirmPressEvent:
                return super.Idle;
            case cancelPressEvent:
                return super.ItemSelection;
            default:
                return this;
        }
    }


    protected void enter()
    {
        System.out.println("ENTERED: SelectionValidation");
        System.out.println("Are you ready to make your purchase?");
        //note: this is going to have to go through the gui and NOT through the console
        int lenght = vendingMachine.getDisplay().length();
        vendingMachine.getDisplay().delete(0, lenght);
    }
}
