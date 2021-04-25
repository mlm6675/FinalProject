package State;
import Sources.*;

public abstract class State {
    public final int digitPressEvent = 1;
    public final int programmableButtonPressEvent = 2;
    public final int confirmPressEvent = 3;
    public final int cancelPressEvent = 4;
    public final int arrowUpEvent = 5;
    public final int arrowDownEvent = 6;
    public final int moneyEnteredEvent = 7;
    public final int filterPressEvent = 8;
    protected VendingMachineImpl vendingMachine;


    State(){}
    State(VendingMachineImpl vendingMachine)
    {
        this.vendingMachine = vendingMachine;
    }

    public abstract State processEvent(int event);
    protected abstract State nextState(int event);
    protected void enter()
    {

    }
}
