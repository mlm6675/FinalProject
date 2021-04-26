package DynamicLinkagePattern;

import FactoryMethodPattern.Item;
import Sources.Inventory;
import Sources.VendingMachine;

public abstract class BundleProgram extends AbsVMProgram{
    protected String[] items;
    BundleProgram(VendingMachine vm, String... items)
    {
        setEnvironment(vm);
        this.items = items;
    }

    public void run()
    {
        if(super.machine != null){
            Inventory inv = machine.getInventory();
            double balance = machine.getBalance(), totals = 0;
            //Get all items to sell & calculate the totals
            Item[] ref = inv.getItemsByName(items);
            for (Item i : ref) {
                totals+=i.getPrice();
            }

            //Make sure there's enough money to buy the bundle
            if((balance-totals)>=0){
                for(Item i : ref){
                    machine.setSelectedItem(i);
                    machine.dispense();
                }
                machine.setBalance(balance-totals);
            }
        }
    }

    public boolean isCompatible()
    {
        Inventory inv = super.machine.getInventory();
        return inv.containsAll(items);
    }
}
