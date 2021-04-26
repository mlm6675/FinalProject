package AdapterPattern;

import javax.swing.*;

public interface Display {
    void setDispalyText(String text);
    String getDisplayText();
    JButton[] getButtons();
}
