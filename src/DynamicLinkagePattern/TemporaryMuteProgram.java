package DynamicLinkagePattern;

import Sources.VendingMachine;

public class TemporaryMuteProgram extends AbsVMProgram{

    public TemporaryMuteProgram(VendingMachine vm){
        setEnvironment(vm);
    }
    @Override
    public void run() {
        if(machine!=null){
            machine.toggleMute();
        }
    }

    @Override
    public boolean isCompatible() {
        return true;
    }

}
