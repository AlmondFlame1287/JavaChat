package org.mike.gui.components;

import org.mike.Contact;

import javax.swing.*;
import java.awt.*;

public class ContactArea extends JPanel {

    public ContactArea(Contact current) {
        this.setLayout(null);
        this.setSize(new Dimension(853, 60));
        this.setup(current);
    }

    private void setup(Contact current) {
        // TODO: Further development needed
        this.setBorder(BorderFactory.createEtchedBorder());

        JLabel label;
        if(current == null) {
             label = new JLabel("Test");
        } else {
            label = new JLabel(current.getName());
        }

        label.setBounds(300, 20, 200, 20);
        this.add(label);
    }
}
