package DynamicLinkagePattern;

import Sources.VendingMachine;

public class NullProgram extends AbsVMProgram {
    public NullProgram(VendingMachine vm){
        setEnvironment(vm);
    }
    @Override
    public void run() {
        //do absolutely nothing :)
    }

    @Override
    public boolean isCompatible() {
        return true;
    }
}
