package State;
import Sources.*;

public class Idle extends State {
    private static Idle instance = new Idle(); //?? i dont think this is going to work, probs going to have to make VendingMachineImpl into a singleton as well

    private Idle(VendingMachineImpl vm) { super(vm);
    }
    private Idle() {
    }

    public static State getInstance(){
        return instance;
    }

    @Override
    public State processEvent(int event) {
        return null;
    }

    @Override
    protected State nextState(int event) {

        switch(event)
        {
            case digitPressEvent, programmableButtonPressEvent, confirmPressEvent, cancelPressEvent, arrowUpEvent, arrowDownEvent, filterPressEvent:
                return this;
            case moneyEnteredEvent:
                return ItemSelection.getInstance();
            default:
                System.out.println("unexpected state encountered. returning to idle state.");
                return Idle.getInstance();
        }

    }
}
