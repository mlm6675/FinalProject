package DynamicLinkage;

public interface VMProgram {
    void setEnvironment(VendingMachine vm);
    void run();
    boolean isCompatible();
}
