package org.mike.gui.components;

import org.mike.Contact;
import org.mike.Message;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MessageArea extends JPanel {
    private ArrayList<Message> messages;

    public MessageArea(Contact current) {
        this.setLayout(null);
        this.messages = this.readMessages(current);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.drawMessages(g);
    }

    private ArrayList<Message> readMessages(Contact current) {
        return null;
    }

    private void drawMessages(Graphics g) {
        System.out.println("Can't draw anything right now");
    }
}
