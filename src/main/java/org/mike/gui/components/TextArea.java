package org.mike.gui.components;

import javax.swing.*;
import java.awt.*;

public class TextArea extends JPanel {
    private JTextField messageField;
    private JButton send;

    public TextArea() {
        this.setLayout(null);
        this.setSize(new Dimension(853, 120));
        this.setup();
    }

    private void setup() {
        this.messageField = new JTextField();
        this.send = new JButton("Send");
        this.send.addActionListener(evt -> this.onSend());

        this.setBorder(BorderFactory.createEtchedBorder());

        this.messageField.setBounds(100, 10, 450, 20);
        this.send.setBounds(570, 10, 200, 20);

        this.add(this.send);
        this.add(this.messageField);
    }

    private void onSend() {
        // TODO: Continue development
        System.out.println("Further development needed");
//        System.out.println("Message:" + messageField.getText());
        MessageArea.getInstance().addMessage(messageField.getText());
//        String messageToSend = this.messageField.getText();
    }
}
