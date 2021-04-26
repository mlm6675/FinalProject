package Sources;
import AdapterPattern.Display;
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
    private Display screen;

    public VendingMachineImpl(int size) {
        programmableButtons[0] = new SaladWaterBundle(this);
        programmableButtons[1] = new SodaCandyBundle(this);
        programmableButtons[2] = new TemporaryMuteProgram(this);

        inventory = new Inventory(size);
        currentState = State.setEnvironment(this);
        currentSelection = new Null_Item();
        balance = 0;
        currentDeposit = 0;
        isSilent = false;
    }

    public void setOutputScreen(Display component){
        screen = component;
        StringBuilder msg = new StringBuilder();
        msg.append("Welcome to the Generic Vending Machine :3\n");
        msg.append("Please, enter money to activate the machine.");
        screen.setDispalyText(msg.toString());
    }

    @Override
    public State getCurrentState() {
        return currentState;
    }

    @Override
    public void runProgram(int key) {
        programmableButtons[key-1].run();
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
            message = String.format( "The amount refunded > $%.2f", balance);
        }
        JOptionPane.showMessageDialog(null, message);
        balance = 0;
    }
    public void toggleMute()
    {
        isSilent = !isSilent;
        if(isSilent){
            JOptionPane.showMessageDialog(null, "Machine is muted.");
        }else{
            JOptionPane.showMessageDialog(null, "Machine is unmuted.");
        }
    }

    @Override
    public Display getDisplay() {
        return screen;
    }

}
