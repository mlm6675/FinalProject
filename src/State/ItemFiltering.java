package State;
import FactoryMethodPattern.Item;
import FilterPattern.ItemFilter;
import VendingMachine.*;

public class ItemFiltering extends State {
    private static ItemFiltering instance = new ItemFiltering(); //?? i dont think this is going to work, probs going to have to make VendingMachineImpl into a singleton as well

    private ItemFiltering(VendingMachineImpl vm)
    {
        super(vm);
    }
    private ItemFiltering(){};

    @Override
    public State processEvent(int event) {
        return null;
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
}
