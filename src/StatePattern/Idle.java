package StatePattern;
import AdapterPattern.Display;

import javax.swing.*;

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
            else
                JOptionPane.showMessageDialog(null, "The money you've entered is fake.");
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
                if(isRealMoney){
                    vendingMachine.getCurrentState().leave();
                    State.ItemSelection.enter();
                    return State.ItemSelection;
                }else{
                    return this;
                }
            default:
                return this;
        }

    }

    @Override
    protected void leave() {
        //resets the state
        super.leave();
        isRealMoney = false;
    }

    @Override
    protected void enter()
    {
        super.enter();
        Display display = vendingMachine.getDisplay();
        StringBuilder msg = new StringBuilder();
        msg.append("Welcome to the Generic Vending Machine :3\n");
        msg.append("Please, enter money to activate the machine.");
        display.setDispalyText(msg.toString());
    }
}
