package DynamicLinkage;

public class TemporaryMuteProgram extends AbsVMProgram{

    @Override
    public void run() {
        machine.muteSound(true); //possibly come back to this.
        //are we going to wait for 1 minute and then set this back to false?
        //or are we just going to have it so if someone presses the mute button,
        //and the program is already muted, we unmute?
        //not a big deal, but something to keep in mind.
    }

    @Override
    public boolean isCompatible() {
        return false;
    }

}
