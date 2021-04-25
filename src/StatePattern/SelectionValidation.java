package StatePattern;
import FactoryMethodPattern.Items.Null_Item;
import Sources.*;

public class SelectionValidation extends State{
    private static SelectionValidation instance = new SelectionValidation(VendingMachineImpl.getCurrentInstance());

    private SelectionValidation(VendingMachine vm)
    {
        super(vm);
    }

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
                return Idle.getInstance();
            case cancelPressEvent:
                return ItemSelection.getInstance();
            default:
                return this;
        }
    }

    public static State getInstance()
    {
        instance.enter();
        return instance;
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
