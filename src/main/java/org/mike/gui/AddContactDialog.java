package org.mike.gui;

import org.mike.Contact;
import org.mike.gui.components.ui.CustomButton;
import org.mike.gui.components.ui.CustomTextField;
import org.mike.gui.content.ContactView;

import javax.swing.*;

public class AddContactDialog extends JDialog {
    private CustomTextField name;
    private CustomTextField ip;

    public AddContactDialog() {
        this.setLayout(null);
        this.setSize(300, 300);
        this.initText();
        this.setLocationRelativeTo(null);
        this.initButtons();
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    private void initText() {
        final JLabel nameLabel = new JLabel("Contact name:");
        final JLabel ipLabel = new JLabel("IP:");

        this.name = new CustomTextField();
        this.ip = new CustomTextField();

        this.add(nameLabel);
        this.add(name);
        this.add(ipLabel);
        this.add(ip);

        nameLabel.setBounds(100, 100, 200, 20);
        name.setBounds(100, 120, 100, 20);

        ipLabel.setBounds(100, 140, 200, 20);
        ip.setBounds(100, 160, 100, 20);
    }

    private void initButtons() {
        final CustomButton done = new CustomButton("Done");
        final CustomButton cancel = new CustomButton("Cancel");

        this.add(done);
        this.add(cancel);

        done.setBounds(100, 180, 100, 20);
        cancel.setBounds(100, 200, 100, 20);

        done.addActionListener(evt -> {
            ContactView.getInstance().addContact(new Contact(name.getText(), ip.getText()));
            this.dispose();
        });

        cancel.addActionListener(evt -> this.dispose());
    }
}
