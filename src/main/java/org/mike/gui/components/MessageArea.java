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
        this.setLayout(null);
        this.messages = new ArrayList<>();
    }

    public static MessageArea getInstance() {
        if(instance == null)
            instance = new MessageArea();
        return instance;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.drawMessages(g);
    }

    public void addMessage(String text) {
        LocalDateTime dateTime = LocalDateTime.now();
        // TODO: Localize the sender of the message
        // As for now, it's only gonna be the dev
        String sender = User.getUser().getName();
        Message msg = new Message(dateTime, text, sender);

        this.messages.add(msg);
        msg.draw(this.getGraphics());
    }

    public void readMessages() {
        File contactFile = ContactView.getInstance().getPressedContact().getMessageFile();
        if(!contactFile.exists())
            return;

        ArrayList<Message> messageArray = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(contactFile))) {
            String line;
            while((line = br.readLine()) != null) {
                String[] splits = line.split(",");
                messageArray.add(new Message(LocalDateTime.parse(splits[0]), splits[1], splits[2]));
            }
        } catch(IOException ioe) {
            System.err.println("There was a problem reading messages: " + ioe.getMessage());
        }

        messages = messageArray;
    }

    private void drawMessages(Graphics g) {
        if(messages == null)
            return;

        for(Message msg : messages) {
            msg.draw(g);
        }
    }
}
