package org.mike.gui.components;

import org.mike.Contact;

import javax.swing.*;
import java.awt.*;

import static org.mike.common.Constants.*;

// TODO: Continue solving problems about resolution and sizing
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
        this.setSize(new Dimension(RIGHT_COMPONENTS_WIDTH, COMMON_HEIGHT / HEIGHT_DIVIDE_FACTOR));
        this.setup();
    }

    private void setup() {
        this.setBorder(BorderFactory.createEtchedBorder());

        this.contactName = new JLabel();

        final int w = 150;
        final int h = 30;
        final int x = (this.getSize().width - w) / 2;
        final int y = (this.getSize().height - h) / 2;

        // Default values 200, 35, 200, 20
        this.contactName.setBounds(x, y, w, h);
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
