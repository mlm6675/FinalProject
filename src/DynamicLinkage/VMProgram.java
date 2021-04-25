package DynamicLinkage;

import Sources.VendingMachine;

public interface VMProgram {
    void setEnvironment(VendingMachine vm);
    void run();
    boolean isCompatible();
}
