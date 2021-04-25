package VendingMachine;
import FactoryMethodPattern.*;


public interface VendingMachine {
    void buttonPress(int event);
    void setSelectedItem(Item item);
    Inventory getInventory();
    void setBalance(double b);
    double getBalance();
    void dispense();
    void refund();
    void muteSound(boolean st);
}
