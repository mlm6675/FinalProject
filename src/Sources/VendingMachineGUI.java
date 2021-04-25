package Sources;

import FactoryMethodPattern.Item;
import FactoryMethodPattern.ItemFactory;
import FactoryMethodPattern.ItemFactoryImp;
import FactoryMethodPattern.Items.*;
import FilterPattern.Filters.EggFilter;
import FilterPattern.Filters.LactoseFilter;
import FilterPattern.SourceList;

import javax.swing.*;
import java.util.ArrayList;

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

        //Factory Pattern Test
        factoryPatternTest();

        //Filter Pattern Test
        filterPatternTest();

        //Testing inventory loading
        testingInventoryCreation();
    }

    private static void testingInventoryCreation() {
        Inventory inv = new Inventory(9);
        for(int i = 1; i != 10; i++){
            Item item = inv.getItemNumber(i);
            System.out.println("Item: \""+ item.getName() + "\" " + "Count: " + inv.getItemQuantity(item) + " Mask: " + inv.getMask(item));
        }
    }

    private static void filterPatternTest() {
        Item[] items = {new Candy(), new Chips(), new Salad(), new Sandwitch(), new Popcorn(), new Soup()};
        show("Before filtering Lactose and Eggs",items);
        SourceList sourceList = new SourceList(items);
        LactoseFilter lactoseFilter = new LactoseFilter(sourceList);
        EggFilter eggFilter = new EggFilter(lactoseFilter);
        items = eggFilter.applyFilter();
        show("After filtering Lactose and Eggs", items);
    }

    private static void show(String message, Item[] items) {
        System.out.println(message);
        for (Item i : items) {
            System.out.print("\""+i.getName() + "\" ");
        }
        System.out.println();
    }

    private static void factoryPatternTest() {
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
