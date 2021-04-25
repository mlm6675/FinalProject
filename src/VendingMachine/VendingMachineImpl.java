package VendingMachine;
import State.*;
import DynamicLinkage.VMProgram;

public class VendingMachineImpl {
    private VMProgram[] programmableButtons = new VMProgram[3];
    private Inventory inventory;
    private State currentState;
    private Item currentSelection;
    private double balance;
    private boolean isSilent;

    VendingMachineImpl() {
        //idk
    }

    public void buttonPress(int event) {

    }

    public void buttonPress(int event, int key) {

    }

    public void setSelectedItem(Item item) {
        this.item = currentSelection;
    }

    public Inventory getInventory() {

    }

    public void setBalance(double b) {
        this.balance = b;
    }

    public double getBalance()
    {
        return balance;
    }
    public void dispense()
    {

    }
    public void refund()
    {

    }
    public void muteSound(boolean st)
    {

    }

}
