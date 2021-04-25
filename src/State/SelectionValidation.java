package State;
import Sources.*;

public class SelectionValidation extends State{
    private static SelectionValidation instance = new SelectionValidation();

    private SelectionValidation(){}
    private SelectionValidation(VendingMachine vm)
    {
        super(vm);
    }

    @Override
    public State processEvent(int event) {
        switch(event)
        {
            case moneyEnteredEvent, digitPressEvent, programmableButtonPressEvent, arrowUpEvent, arrowDownEvent, filterPressEvent:
                return nextState(event); //basically just ignore
            case confirmPressEvent:
                vendingMachine.dispense(); //dispense the item
                //probably going to have to give change too
                return nextState(event);
            case cancelPressEvent:
                //do nothing and return to item selection
                return nextState(event);
            default:
                System.out.println("unexpected state encountered. returning to idle state.");
                return Idle.getInstance();
        }
    }

    @Override
    protected State nextState(int event) {
        switch(event)
        {
            case moneyEnteredEvent, digitPressEvent, programmableButtonPressEvent, arrowUpEvent, arrowDownEvent, filterPressEvent:
                return this;
            case confirmPressEvent:
                //somehow return this instance with the filter attached
                return ItemSelection.getInstance();
            case cancelPressEvent:
                //disregard the changes from the filter
                return Idle.getInstance();
            default:
                System.out.println("unexpected state encountered. returning to idle state.");
                return Idle.getInstance();
        }
    }

    public static State getInstance()
    {
        return instance;
    }

    protected void enter()
    {
        System.out.println("ENTERED: SelectionValidation");
        System.out.println("Are you ready to make your purchase?");
        //note: this is going to have to go through the gui and NOT through the console
    }
}
