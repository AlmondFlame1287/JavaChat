package org.mike.gui.components;

import org.mike.Contact;

import javax.swing.*;
import java.awt.*;

public class ContactArea extends JPanel {
    private static ContactArea instance = null;
    private JLabel contactName;

    public static ContactArea getInstance() {
        if(instance == null)
            instance = new ContactArea();
        return instance;
    }

    private ContactArea() {
        this.setLayout(null);
        this.setSize(new Dimension(853, 60));
        this.setup();
    }

    private void setup() {
        // TODO: Further development needed
        this.setBorder(BorderFactory.createEtchedBorder());

        this.contactName = new JLabel();

        this.contactName.setBounds(300, 20, 200, 20);
        this.add(this.contactName);
    }

    public void setContact(Contact current) {
        contactName.setText(current.getName());
        // TODO: Load profile picture
        // TODO: Load current's conversation

        this.revalidate();
    }

    public String getContactName() {
        return contactName.getText();
    }
}
