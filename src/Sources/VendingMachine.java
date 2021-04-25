package Sources;
import FactoryMethodPattern.*;


public interface VendingMachine {
    void buttonPress(int special_key);
    void buttonPress(int event, int digit_key);
    void setSelectedItem(Item item);
    Item getSelectedItem();
    Inventory getInventory();
    void setBalance(double b);
    double getBalance();
    void setCurrentDeposit(double b);
    double getCurrentDeposit();
    void dispense();
    void refund();
    void muteSound(boolean st);
    StringBuilder getDisplay();
    void appendToDisplay(char ch);

    void setDisplay(String toString);

}
