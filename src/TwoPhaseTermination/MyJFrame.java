package TwoPhaseTermination;

import javax.swing.*;

public class MyJFrame extends JFrame {
    private boolean isReadyToClose = false;

    public MyJFrame(String title){
        super(title);
    }

    @Override
    public void dispose() {
        if(isReadyToClose != false)
            super.dispose();
        else
            isReadyToClose = true;
    }

    public boolean isClosed(){
        return isReadyToClose;
    }
}
