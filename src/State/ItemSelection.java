package State;
import Sources.*;

public class ItemSelection extends State{
    private static ItemSelection instance = new ItemSelection();

    private ItemSelection(VendingMachineImpl vm) { super(vm); }
    private ItemSelection() { }

    @Override
    public State processEvent(int event) {
        return null;
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
}
