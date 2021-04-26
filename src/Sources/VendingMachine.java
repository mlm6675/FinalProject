package Sources;
import AdapterPattern.Display;
import FactoryMethodPattern.*;
import StatePattern.State;

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
    Display getDisplay();
    void setOutputScreen(Display display);
    State getCurrentState();
}
