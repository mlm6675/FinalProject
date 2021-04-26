package AdapterPattern;

import javax.swing.*;

public class JTextAreaAdapter implements Display{
    private JTextArea component;

    public JTextAreaAdapter(JTextArea component){
        this.component = component;
    }


    @Override
    public void setDispalyText(String text) {
        component.setText(text);
        component.updateUI();
    }

    @Override
    public String getDisplayText() {
        return component.getText();
    }
}
