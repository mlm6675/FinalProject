package Sources;
import DynamicLinkage.*;
import FactoryMethodPattern.Item;
import FactoryMethodPattern.Items.Salad;
import FactoryMethodPattern.Items.Soda;
import State.*;

public class VendingMachineImpl implements VendingMachine {
    private VMProgram[] programmableButtons = new VMProgram[3];
    private Inventory inventory;
    private State currentState;
    private Item currentSelection;
    private double balance;
    private boolean isSilent;
    private static VendingMachine currentInstance = new VendingMachineImpl(); //*****************

    VendingMachineImpl() {
        //idk
    }

    public static VendingMachine getCurrentInstance()
    {
        return currentInstance;
    }

    public void buttonPress(int event) {

    }

    public void buttonPress(int event, int key) {

    }

    public void setSelectedItem(Item item) {
        currentSelection = item;
    }

    public Inventory getInventory() {
        return null;
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
        //for now i guess just output text to the terminal
        System.out.println("dispensed " + currentSelection.toString());
    }
    public void refund()
    {

    }
    public void muteSound(boolean st)
    {
        isSilent = st;
    }

    public static void main(String[] args) {
        //dynamic linkage testing!!
        //let's just pretend we already linked the gui buttons as needed

        System.out.println("SaladWaterBundle test");
        AbsVMProgram program = new SaladWaterBundle();
        program.setEnvironment(currentInstance);
        program.run();

        System.out.println("\nSodaCandyBundle test");
        program = new SodaCandyBundle();
        program.setEnvironment(currentInstance);
        program.run();

        System.out.println("\nTemporaryMuteProgram test");
        program = new TemporaryMuteProgram();
        program.setEnvironment(currentInstance);
        program.run();


        System.out.println("\nNullProgram test");
        program = new NullProgram();
        program.setEnvironment(currentInstance);
        program.run();

        //notes:
        //seems to work, but i think i'm supposed to be doing the "class forname" thing
        //would not be difficult to implement a factory for this but i'm not sure if it's necessary
        //either way, we have the external programs controlling this implementation of the vending machine
        //
    }

}
