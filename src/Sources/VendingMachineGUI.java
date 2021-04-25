package Sources;

import javax.swing.*;

public class VendingMachineGUI {
    private JPanel mainFrame;
    private JButton allergyBtn;
    private JButton upBtn;
    private JButton downBtn;
    private JButton btn9;
    private JButton btn3;
    private JButton btn0;
    private JButton cancelBtn;
    private JButton btn6;
    private JButton reprogrammableBtn3;
    private JButton reprogrammableBtn2;
    private JButton btn8;
    private JButton btn5;
    private JButton btn2;
    private JButton btn1;
    private JButton btn4;
    private JButton btn7;
    private JButton acceptBtn;
    private JButton reprogrammableBtn1;
    private JLabel machineWindow;
    private JTextArea screen;

    VendingMachineGUI()
    {

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Vending Machine :)");
        frame.setContentPane(new VendingMachineGUI().mainFrame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        machineWindow = new JLabel(new ImageIcon("vendingWindow.png"));
    }
}
