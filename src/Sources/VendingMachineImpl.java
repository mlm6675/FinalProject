package Sources;
import DynamicLinkagePattern.*;
import FactoryMethodPattern.Item;
import FactoryMethodPattern.Items.Null_Item;
import StatePattern.*;

import javax.swing.*;

public class VendingMachineImpl implements VendingMachine {
    private VMProgram[] programmableButtons = new VMProgram[3];
    private Inventory inventory;
    private State currentState;
    private Item currentSelection;
    private double balance, currentDeposit;
    private boolean isSilent;

    private StringBuilder display; //TODO: replace by GUI textArea

    VendingMachineImpl(int size) {
        //TODO: implement programmable button initialization
        programmableButtons[0] = new NullProgram();
        programmableButtons[1] = new NullProgram();
        programmableButtons[2] = new NullProgram();

        display = new StringBuilder();
        inventory = new Inventory(size);
        currentState = State.setEnvironment(this);
        currentSelection = new Null_Item();
        balance = 0;
        currentDeposit = 0;
        isSilent = false;
    }

    public void buttonPress(int special_key) {
        currentState = currentState.processEvent(special_key);
    }

    public void buttonPress(int event, int digit_key) {
        currentState = currentState.processEvent(event, digit_key);
    }

    public void setSelectedItem(Item item) {
        currentSelection = item;
    }

    @Override
    public Item getSelectedItem() {
        return currentSelection;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setBalance(double b) {
        if(b>=0)
            this.balance = b;
    }
    public double getBalance()
    {
        return balance;
    }
    public void setCurrentDeposit(double d) {
            this.currentDeposit = d;
    }
    public double getCurrentDeposit()
    {
        return currentDeposit;
    }
    public void dispense()
    {
        //It's a mock function. In real machine it will have code to dispense a particular item.
        if(!(currentSelection instanceof Null_Item)){
            inventory.updateItemQuantity(currentSelection, -1);
            JOptionPane.showMessageDialog(null, "The machine is dispensing \"" + currentSelection.getName() + "\".");
        }
    }
    public void refund() {
        //Another mock function. In a real machine it will have code to refund the user using appropriate bills and coins
        String message;
        if(balance == 0){
            message = "There's nothing to refund. You've spent all of your money.";
        }else{
            message = String.format( "The amount refunded > $%.2", balance);
        }
        JOptionPane.showMessageDialog(null, message);
    }
    public void muteSound(boolean st)
    {
        isSilent = st;
    }

    @Override
    public StringBuilder getDisplay() {
        return display;
    }

    @Override
    public void appendToDisplay(char ch) {
        display.append(ch);
    }

    @Override
    public void setDisplay(String toString) {
        System.out.println("----------------");
        System.out.println(toString);
        System.out.println("----------------");
    }

}
