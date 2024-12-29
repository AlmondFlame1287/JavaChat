package org.mike.gui.components;

import org.mike.Message;
import org.mike.User;
import org.mike.connection.Server;
import org.mike.gui.components.customs.CustomButton;
import org.mike.gui.components.customs.CustomTextField;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

import static org.mike.common.Constants.*;

public class TextArea extends JPanel {
    private final CustomTextField messageField;

    public TextArea() {
        this.messageField = new CustomTextField();
        this.initArea();
        this.addComponents();
    }

    private void initArea() {
        this.setPreferredSize(new Dimension(RIGHT_VIEW_WIDTH, FRAME_HEIGHT /     RIGHT_VIEW_HEIGHT_DIVIDE_FACTOR));
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
    }

    private void addComponents() {
        final CustomButton send = new CustomButton("Send");
        send.addActionListener(evt -> this.onSend());

        this.messageField.setMaximumSize(new Dimension(400, 25));
        this.add(this.messageField);
        this.add(send);
    }

    private void onSend() {
        Message message = new Message(LocalDateTime.now(), messageField.getText(), User.getUser().getName());

        Server.getInstance().send(message);
        MessageArea.getInstance().addMessage(message);
    }
}
