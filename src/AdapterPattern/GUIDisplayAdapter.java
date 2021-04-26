package AdapterPattern;

import javax.swing.*;

public class GUIDisplayAdapter implements Display{
    private JTextArea component;
    private JButton[] buttons;

    public GUIDisplayAdapter(JTextArea component, JButton... buttons){
        this.component = component;
        this.buttons = buttons;
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

    @Override
    public JButton[] getButtons() {
        return buttons;
    }
}
