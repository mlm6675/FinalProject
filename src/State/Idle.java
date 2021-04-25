package State;
import Sources.*;

public class Idle extends State {
    private static final Idle instance = new Idle(VendingMachineImpl.getCurrentInstance()); //?? i dont think this is going to work, probs going to have to make VendingMachineImpl into a singleton as well

    private Idle(VendingMachine vm) { super(vm); }
    public static State getInstance(){
        return instance;
    }

    @Override
    public State processEvent(int event) {
        switch(event)
        {
            case digitPressEvent, programmableButtonPressEvent, confirmPressEvent, cancelPressEvent, arrowUpEvent, arrowDownEvent, filterPressEvent:
                return nextState(event);
            case moneyEnteredEvent:
                //going to add some checks in place to ensure the money entered is valid
                System.out.println("Assuming balance is valid and going to the next state. Will add checks later.");
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
            case digitPressEvent, programmableButtonPressEvent, confirmPressEvent, cancelPressEvent, arrowUpEvent, arrowDownEvent, filterPressEvent:
                return this;
            case moneyEnteredEvent:
                return ItemSelection.getInstance();
            default:
                System.out.println("unexpected state encountered. returning to idle state.");
                return Idle.getInstance();
        }

    }

    protected void enter()
    {
        System.out.println("ENTERED: Idle");
        System.out.println("Hello! Please insert money to start.");
        //note: this is going to have to go through the gui and NOT through the console
    }
}
