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
    private CustomTextField messageField;

    public TextArea() {
        this.setLayout(null);
        this.setPreferredSize(new Dimension(RIGHT_COMPONENTS_WIDTH, COMMON_HEIGHT / HEIGHT_DIVIDE_FACTOR));
        this.setup();
    }

    private void setup() {
        this.messageField = new CustomTextField();
        final CustomButton send = new CustomButton("Send");
        send.addActionListener(evt -> this.onSend());

        this.setBorder(BorderFactory.createEtchedBorder());

//        final int msgFieldW = this.getWidth() / WIDTH_DIVIDE_FACTOR;
//        final int elemH = 30;
//        final int msgFieldX = this.getWidth() / 2 - msgFieldW;
//        final int msgFieldY = this.getHeight() / 2 - elemH;


        final int msgFieldW = this.getPreferredSize().width / WIDTH_DIVIDE_FACTOR;
        final int elemH = 30;
        final int msgFieldX = this.getPreferredSize().width / 2 - msgFieldW;
        final int msgFieldY = this.getPreferredSize().height / 2 - elemH;

        final int sendBtnW = 150;
        final int sendBtnX = msgFieldX + msgFieldW;
        final int sendBtnY;
        sendBtnY = msgFieldY;

        this.messageField.setBounds(msgFieldX, msgFieldY, msgFieldW, elemH);
        send.setBounds(sendBtnX, sendBtnY, sendBtnW, elemH);

        this.add(send);
        this.add(this.messageField);
    }

    private void onSend() {
        Message message = new Message(LocalDateTime.now(), messageField.getText(), User.getUser().getName());

        Server.getInstance().send(message);
        MessageArea.getInstance().addMessage(message);
    }
}
