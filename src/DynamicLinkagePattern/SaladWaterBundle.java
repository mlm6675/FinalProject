package DynamicLinkagePattern;
import FactoryMethodPattern.Items.Salad;
import FactoryMethodPattern.Items.Water;

public class SaladWaterBundle extends BundleProgram{

    public SaladWaterBundle()
    {
    }

    public void run()
    {
        machine.setSelectedItem(new Salad());
        machine.dispense();
        machine.setSelectedItem(new Water());
        machine.dispense();
}

    public boolean isCompatible()
    {
        return true; //unsure what to do here
    }
}
