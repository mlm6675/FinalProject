package StatePattern;
import Sources.*;

public class Idle extends State {
    private boolean isRealMoney;
    public Idle() {
        isRealMoney = false;
    }

    @Override
    public State processEvent(int event) {
        if(event == State.moneyEnteredEvent){
            isRealMoney = validateMoney();
            if(isRealMoney)
                vendingMachine.setBalance(vendingMachine.getCurrentDeposit());
            vendingMachine.setCurrentDeposit(0);
        }
        return nextState(event);
    }


    @Override
    public State processEvent(int event, int key) {
        return nextState(event);
    }

    @Override
    protected State nextState(int event) {

        switch(event)
        {
            case digitPressEvent, programmableButtonPressEvent, confirmPressEvent, cancelPressEvent, arrowUpEvent, arrowDownEvent, filterPressEvent:
                return this;
            case moneyEnteredEvent:
                return (isRealMoney)?super.ItemSelection:this;
            default:
                System.err.println("Unexpected state encountered.");
                return this;
        }

    }

    @Override
    protected void leave() {
        //resets the state
        isRealMoney = false;
    }

    @Override
    protected void enter()
    {
        System.out.println("ENTERED: Idle");
        System.out.println("Hello! Please insert money to start.");
        //note: this is going to have to go through the gui and NOT through the console
    }
}
