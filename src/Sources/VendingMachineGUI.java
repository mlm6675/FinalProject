package Sources;

import AdapterPattern.GUIDisplayAdapter;
import FactoryMethodPattern.Item;
import FactoryMethodPattern.ItemFactory;
import FactoryMethodPattern.ItemFactoryImp;
import FactoryMethodPattern.Items.*;
import FilterPattern.Filters.EggFilter;
import FilterPattern.Filters.LactoseFilter;
import FilterPattern.SourceList;
import StatePattern.State;
import TwoPhaseTermination.MyJFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VendingMachineGUI {
    private VendingMachine vm;
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
    private JButton enterMoneyBtn;

    VendingMachineGUI(VendingMachine vm) {
        this.vm = vm;
        JButton[] buttons = { btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9};
        this.vm.setOutputScreen(new GUIDisplayAdapter(screen, buttons));
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vm.buttonPress(State.digitPressEvent, 1);
            }
        });
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vm.buttonPress(State.digitPressEvent, 2);
            }
        });
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vm.buttonPress(State.digitPressEvent, 3);
            }
        });
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vm.buttonPress(State.digitPressEvent, 4);
            }
        });
        btn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vm.buttonPress(State.digitPressEvent, 5);
            }
        });
        btn6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vm.buttonPress(State.digitPressEvent, 6);
            }
        });
        btn7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vm.buttonPress(State.digitPressEvent, 7);
            }
        });
        btn8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vm.buttonPress(State.digitPressEvent, 8);
            }
        });
        btn9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vm.buttonPress(State.digitPressEvent, 9);
            }
        });
        btn0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vm.buttonPress(State.digitPressEvent, 0);
            }
        });
        acceptBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vm.buttonPress(State.confirmPressEvent);
            }
        });
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vm.buttonPress(State.cancelPressEvent);
            }
        });
        upBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vm.buttonPress(State.arrowUpEvent);
            }
        });
        downBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vm.buttonPress(State.arrowDownEvent);
            }
        });
        allergyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vm.buttonPress(State.filterPressEvent);
            }
        });
        reprogrammableBtn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vm.buttonPress(State.programmableButtonPressEvent, 1);
            }
        });
        reprogrammableBtn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vm.buttonPress(State.programmableButtonPressEvent, 2);
            }
        });
        reprogrammableBtn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vm.buttonPress(State.programmableButtonPressEvent, 3);
            }
        });
        enterMoneyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Amount of money entered (negative values for fake money): ");
                try{
                    double amount = Double.parseDouble(input);
                    if(!Double.isNaN(amount)){
                        vm.setCurrentDeposit(amount);
                        vm.buttonPress(State.moneyEnteredEvent);
                    }
                }catch (NumberFormatException ex){
                    //ignore input
                }
            }
        });
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

    public Container getContentPane() {
        return mainFrame;
    }
}
