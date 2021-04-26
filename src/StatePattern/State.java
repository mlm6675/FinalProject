package StatePattern;
import Sources.*;

public abstract class State {
    //Events
    public static final int digitPressEvent = 1;
    public static final int programmableButtonPressEvent = 2;
    public static final int confirmPressEvent = 3;
    public static final int cancelPressEvent = 4;
    public static final int arrowUpEvent = 5;
    public static final int arrowDownEvent = 6;
    public static final int moneyEnteredEvent = 7;
    public static final int filterPressEvent = 8;

    //States
    protected static State Idle;
    protected static State ItemSelection;
    protected static State ItemFiltering;
    protected static State SelectionValidation;

    //VM reference
    protected static VendingMachine vendingMachine;

    public abstract State processEvent(int event);
    public abstract State processEvent(int event, int key);
    protected abstract State nextState(int event);

    protected void enter() {
        System.out.println("ENTERED: " + this.getClass().getSimpleName());
    }
    protected void leave() {
        System.out.println("LEFT: " + this.getClass().getSimpleName());
    }
    protected boolean validateMoney() {
        double deposit = vendingMachine.getCurrentDeposit();
        return  (deposit>=0)? true : false;
    }

    public static State setEnvironment(VendingMachine vm){
        vendingMachine = vm;
        Idle = new Idle();
        ItemSelection = new ItemSelection();
        ItemFiltering = new ItemFiltering();
        SelectionValidation = new SelectionValidation();
        return Idle;
    }
}
