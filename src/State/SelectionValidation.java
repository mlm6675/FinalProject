package State;
import VendingMachine.*;

public class SelectionValidation extends State{
    private static SelectionValidation instance = new SelectionValidation();

    private SelectionValidation(){}
    private SelectionValidation(VendingMachineImpl vm)
    {
        super(vm);
    }

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
}
