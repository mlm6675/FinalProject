import FactoryMethodPattern.Item;
import FactoryMethodPattern.ItemFactory;
import FactoryMethodPattern.ItemFactoryImp;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

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

        ItemFactory factory = new ItemFactoryImp();
        ArrayList<Item> items = new ArrayList<>();
        items.add(factory.getItemByName("Soda"));
        items.add(factory.getItemByName("Salad"));
        items.add(factory.getItemByName("Candy"));
        items.add(factory.getItemByName("Pork"));
        for (Item i: items) {
            System.out.print("Name: "+ i.getName() + "\tCost: " + i.getPrice());
            String[] allergens = i.getAllergenList();
            for(int x = 0, size = allergens.length; x !=size; x++){
                System.out.print(allergens[x]+" ");
            }
            System.out.println();
        }
    }

    private void createUIComponents() {
        machineWindow = new JLabel(new ImageIcon("vendingWindow.png"));
    }
}
