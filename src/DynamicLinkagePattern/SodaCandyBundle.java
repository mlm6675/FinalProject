package DynamicLinkagePattern;

import FactoryMethodPattern.Items.Candy;
import FactoryMethodPattern.Items.Soda;

public class SodaCandyBundle extends BundleProgram {



    public void run()
    {
        machine.setSelectedItem(new Soda());
        machine.dispense();
        machine.setSelectedItem(new Candy());
        machine.dispense();
    }

    public boolean isCompatible()
    {
        return true;
    }//?????????????
}
