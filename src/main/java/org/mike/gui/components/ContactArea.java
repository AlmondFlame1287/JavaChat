package org.mike.gui.components;

import org.mike.Contact;

import javax.swing.*;
import java.awt.*;

import static org.mike.common.Constants.*;

public class ContactArea extends JPanel {
    private static ContactArea instance = null;
    private Contact contact;
    private JLabel contactName;

    public static ContactArea getInstance() {
        if(instance == null)
            instance = new ContactArea();
        return instance;
    }

    private ContactArea() {
        this.setLayout(null);
        this.setPreferredSize(new Dimension(RIGHT_COMPONENTS_WIDTH, COMMON_HEIGHT / HEIGHT_DIVIDE_FACTOR));
        this.setup();
    }

    private void setup() {
        // TODO: Further development needed
        this.setBorder(BorderFactory.createEtchedBorder());

        this.contactName = new JLabel();

        this.contactName.setBounds(200, 35, 200, 20);
        this.add(this.contactName);
    }

    public void drawProfilePicture() {
        Image toDraw = this.contact.getProfilePicture();
        if(toDraw == null) return;

        toDraw = toDraw.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        this.getGraphics().drawImage(toDraw, 0, 0, null);
    }

    public void setContact(Contact current) {
        this.contact = current;
        this.contactName.setText(this.contact.getName());

        this.revalidate();
    }

    public Contact getContact() {
        return contact;
    }

    public String getContactName() {
        return contactName.getText();
    }
}
