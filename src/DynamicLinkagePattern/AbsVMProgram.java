package DynamicLinkagePattern;
import Sources.*;

public abstract class AbsVMProgram implements VMProgram{
    protected VendingMachine machine;

    public void setEnvironment(VendingMachine machine)
    {
        this.machine = machine;
    }

    public abstract void run();


}
