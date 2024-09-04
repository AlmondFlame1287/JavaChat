package org.mike.gui.components;

import org.mike.connection.Server;

import javax.swing.*;
import java.awt.*;

public class TextArea extends JPanel {
    private JTextField messageField;

    public TextArea() {
        this.setLayout(null);
        this.setSize(new Dimension(853, 120));
        this.setup();
    }

    private void setup() {
        this.messageField = new JTextField();
        JButton send = new JButton("Send");
        send.addActionListener(evt -> this.onSend());

        this.setBorder(BorderFactory.createEtchedBorder());

        this.messageField.setBounds(100, 10, 450, 20);
        send.setBounds(570, 10, 200, 20);

        this.add(send);
        this.add(this.messageField);
    }

    private void onSend() {
        Server.getInstance().send(messageField.getText());
        MessageArea.getInstance().addMessage(messageField.getText());
    }
}
