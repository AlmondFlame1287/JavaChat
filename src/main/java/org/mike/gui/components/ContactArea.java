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
        this.initArea();
    }

    private void initArea() {
        this.setPreferredSize(new Dimension(RIGHT_VIEW_WIDTH, FRAME_HEIGHT / RIGHT_VIEW_HEIGHT_DIVIDE_FACTOR));
        this.setLayout(new GridLayout(1, 3));

        this.contactName = new JLabel();
        this.add(Box.createHorizontalBox());
        this.add(this.contactName);
    }

    public void drawProfilePicture() {
        Image toDraw = this.contact.getProfilePicture();
        if(toDraw == null) return;

        final int height = this.getSize().height;
        final int width = 100;

        toDraw = toDraw.getScaledInstance(width, height, Image.SCALE_SMOOTH);
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
