package org.mike.gui.components;

import org.mike.Message;
import org.mike.User;
import org.mike.connection.Server;
import org.mike.gui.components.customs.CustomButton;
import org.mike.gui.components.customs.CustomTextField;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class TextArea extends JPanel {
    private CustomTextField messageField;

    public TextArea() {
        this.setLayout(null);
        this.setSize(new Dimension(853, 120));
        this.setup();
    }

    private void setup() {
        this.messageField = new CustomTextField();
        final CustomButton send = new CustomButton("Send");
        send.addActionListener(evt -> this.onSend());

        this.setBorder(BorderFactory.createEtchedBorder());

        this.messageField.setBounds(100, 15, 450, 25);
        send.setBounds(570, 15, 200, 25);

        this.add(send);
        this.add(this.messageField);
    }

    private void onSend() {
        Message message = new Message(LocalDateTime.now(), messageField.getText(), User.getUser().getName());

        Server.getInstance().send(message);
        MessageArea.getInstance().addMessage(message);
    }
}
