package TwoPhaseTermination;

import Sources.VendingMachine;
import Sources.VendingMachineGUI;
import Sources.VendingMachineImpl;
import TwoPhaseTermination.MyJFrame;

import javax.swing.*;
import java.awt.event.WindowEvent;

public class Terminator {
    private static VendingMachine instance = new VendingMachineImpl(9);
    private static Thread monitoringThread;

    public static void main(String[] args) throws InterruptedException{
        //Start GUI Thread
        monitoringThread = new Thread(){
            @Override
            public void run() {
                VendingMachineGUI app = new VendingMachineGUI(instance);
                MyJFrame frame = new MyJFrame("Vending Machine :)");
                frame.setContentPane(app.getContentPane());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);

                //Main thread monitors GUI state
                while (!frame.isClosed()){
                    //allow operation with GUI
                    try{
                        synchronized (monitoringThread){
                            monitoringThread.wait(250);
                        }
                    }catch (InterruptedException ex){
                        //If this thread was interrupted, do nothing
                    }
                }

                //After it's done
                shutdown(frame);
                System.out.println("GUI Thread was terminated");
            }
        };
        monitoringThread.start();
    }

    private static void shutdown(JFrame frame) {
        //refund the customer if necessary
        if(instance.getBalance() > 0){
            instance.refund();
        }

        //close the window
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }
}
