package State;
import Sources.*;

public class ItemFiltering extends State {
    private static ItemFiltering instance = new ItemFiltering(); //?? i dont think this is going to work, probs going to have to make VendingMachineImpl into a singleton as well

    private ItemFiltering(VendingMachineImpl vm)
    {
        super(vm);
    }
    private ItemFiltering(){};

    @Override
    public State processEvent(int event) {
        switch(event)
        {
            case moneyEnteredEvent, programmableButtonPressEvent, filterPressEvent:
                //ignore these
                return nextState(event);
            case arrowDownEvent:
                //move display to show the bottom of the list
                return nextState(event);
            case arrowUpEvent:
                //move the display to show the top of the list
                return nextState(event);
            case digitPressEvent:
                //record this
                //put it on the displayyyyyyy
                return nextState(event);
            case confirmPressEvent:
                //somehow return this instance with the filter attached
                return nextState(event);
            case cancelPressEvent:
                //disregard the changes from the filter, go back to the previous state
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
                return ItemSelection.getInstance();
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
        System.out.println("ENTERED: ItemFiltering");
        System.out.println("Select button 1-5 corresponding to your dietary restriction:\nwill add this later");
        //note: this is going to have to go through the gui and NOT through the console
    }
}
