package DynamicLinkagePattern;

public abstract class BundleProgram extends AbsVMProgram{
    private String[] items;
    BundleProgram(String... items)
    {
        this.items = items;
    }
}
