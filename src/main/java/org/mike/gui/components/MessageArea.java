package org.mike.gui.components;

import org.mike.Message;

import javax.swing.*;
import java.util.ArrayList;

public class MessageArea extends JPanel {
    private static MessageArea instance = null;
    private final ArrayList<Message> messages;

    private MessageArea() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.messages = new ArrayList<>();
        this.drawMessages();
    }

    public static MessageArea getInstance() {
        if(instance == null)
            instance = new MessageArea();
        return instance;
    }

    public void addMessage(Message msg) {
        this.messages.add(msg);
        this.add(msg.getMessageLabel());
        this.revalidate();
    }

    private void drawMessages() {
        for(Message msg : messages) {
            this.add(msg.getMessageLabel());
        }

        this.revalidate();
    }
}
