package Sources;

import javax.swing.*;

public class Terminator {
    public static void main(String[] args) throws InterruptedException{
        VendingMachine instance = new VendingMachineImpl(9);
        Thread guiThread = new Thread(){
            @Override
            public void run() {
                VendingMachineGUI app = new VendingMachineGUI(instance);
            }
        };
        SwingUtilities.invokeLater(guiThread);
        guiThread.join();
        System.out.println("GUI Thread was terminated");
        if(instance.getBalance() > 0){
            instance.refund();
        }
    }
}
