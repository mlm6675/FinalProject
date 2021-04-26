package StatePattern;
import AdapterPattern.Display;
import FactoryMethodPattern.Items.Null_Item;

public class SelectionValidation extends State{

    @Override
    public State processEvent(int event) {
        switch(event) {
            case cancelPressEvent:
                vendingMachine.setSelectedItem(new Null_Item());
                break;
            case confirmPressEvent:
                double newBalance = vendingMachine.getBalance() - vendingMachine.getSelectedItem().getPrice();
                vendingMachine.setBalance(newBalance);
                vendingMachine.dispense();
                vendingMachine.refund();
                break;
        }
        return nextState(event);
    }

    @Override
    public State processEvent(int event, int key) {
        return nextState(event);
    }

    @Override
    protected State nextState(int event) {
        State current = vendingMachine.getCurrentState();
        switch(event)
        {
            case confirmPressEvent:
                current.leave();
                State.Idle.enter();
                return State.Idle;
            case cancelPressEvent:
                current.leave();
                State.ItemSelection.enter();
                return State.ItemSelection;
            default:
                return this;
        }
    }

    @Override
    protected void leave() {
        super.leave();
    }

    @Override
    protected void enter()
    {
        super.enter();
        Display screen = vendingMachine.getDisplay();
        StringBuilder msg = new StringBuilder();
        msg.append("Confirm your purchase\\Cancel for refund\n");
        msg.append(String.format("Balance: $%.2f", + vendingMachine.getBalance()));
        msg.append(String.format("\tItem cost: $%.2f", vendingMachine.getSelectedItem().getPrice()));
        msg.append('\n');
        msg.append("Do you want to buy \""+ vendingMachine.getSelectedItem().getName() + "\"?");
        screen.setDispalyText(msg.toString());
    }
}
