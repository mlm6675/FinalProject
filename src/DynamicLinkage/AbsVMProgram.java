package DynamicLinkage;
import VendingMachine.*;

public abstract class AbsVMProgram implements VMProgram{
    protected VendingMachine machine;

    public void setEnvironment(VendingMachine machine)
    {
        this.machine = machine;
    }

    public abstract void run();


}
