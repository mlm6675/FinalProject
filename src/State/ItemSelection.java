package State;
import Sources.*;

public class ItemSelection extends State{
    private static ItemSelection instance = new ItemSelection();

    private ItemSelection(VendingMachine vm) { super(vm); }
    private ItemSelection() { }

    @Override
    public State processEvent(int event) {
        switch(event)
        {
            case digitPressEvent, moneyEnteredEvent:
                //append info to the the screen
                return nextState(event);
            case programmableButtonPressEvent:
                //add bundle information and price to the screen
                return nextState(event);
            case arrowUpEvent, arrowDownEvent, filterPressEvent:
                //dont do anythinggggggggggggggggggggggggggggg, just go to proper state
                return nextState(event);
            case cancelPressEvent:
                //give back money and go back to idle
                return nextState(event);
            case confirmPressEvent:
                //ensure the correct amount of money has been entered before moving on
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
            case digitPressEvent, moneyEnteredEvent, programmableButtonPressEvent, arrowUpEvent, arrowDownEvent:
                return this;
            case cancelPressEvent:
                return Idle.getInstance();
            case confirmPressEvent:
                //only can confirm if a number is entered
                return SelectionValidation.getInstance();
            case filterPressEvent:
                return ItemFiltering.getInstance();
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
        System.out.println("ENTERED: ItemSelection");
        System.out.println("Please make a selection.");
        //note: this is going to have to go through the gui and NOT through the console
    }
}
