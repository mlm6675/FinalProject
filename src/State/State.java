package State;
import Sources.*;

public abstract class State {
    public static final int digitPressEvent = 1;
    public static final int programmableButtonPressEvent = 2;
    public static final int confirmPressEvent = 3;
    public static final int cancelPressEvent = 4;
    public static final int arrowUpEvent = 5;
    public static final int arrowDownEvent = 6;
    public static final int moneyEnteredEvent = 7;
    public static final int filterPressEvent = 8;
    protected VendingMachine vendingMachine;


    State(){}
    State(VendingMachine vendingMachine)
    {
        this.vendingMachine = vendingMachine;
    }

    public abstract State processEvent(int event);
    protected abstract State nextState(int event);
    protected void enter()
    {

    }
    protected void exit()
    {

    }
}
