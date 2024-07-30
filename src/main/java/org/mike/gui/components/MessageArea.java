package org.mike.gui.components;

import org.mike.Message;
import org.mike.User;
import org.mike.gui.content.ContactView;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MessageArea extends JPanel {
    private static MessageArea instance = null;
    private ArrayList<Message> messages;

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

    public void addMessage(String text) {
        // TODO: Localize the sender of the message
        // As for now, it's only gonna be the dev
        LocalDateTime dateTime = LocalDateTime.now();
        String sender = User.getUser().getName();
        Message msg = new Message(dateTime, text, sender);

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
