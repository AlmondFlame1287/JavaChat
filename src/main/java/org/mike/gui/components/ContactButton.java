package org.mike.gui.components;

import org.mike.Contact;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ContactButton extends JButton {
    private final Color hoverBackgroundColor = new Color(0xDDDDDD);;
    private final Color pressedBackgroundColor = new Color(0xCCCCCC);
    private final Contact contact;

    public ContactButton(Contact contact) {
        super(contact.getName());
        this.contact = contact;
        this.setupButton();
        this.setupMouseListener();
    }

    private void setupButton() {
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.setBorderPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed()) {
            g.setColor(pressedBackgroundColor);
        } else if (getModel().isRollover()) {
            g.setColor(hoverBackgroundColor);
        } else {
            g.setColor(getBackground());
        }
        g.fillRect(0, 0, getWidth(), getHeight());

        super.paintComponent(g);
    }

    private void setupMouseListener() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ContactArea.getInstance().setContact(contact);
            }
        });
    }
}
