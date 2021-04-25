package DynamicLinkage;

public class NullProgram extends AbsVMProgram {
    @Override
    public void run() {
        return;
        //do absolutely nothing :)
    }

    @Override
    public boolean isCompatible() {
        return false;
    }
}
