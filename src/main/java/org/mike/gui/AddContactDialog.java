package org.mike.gui;

import org.mike.Contact;
import org.mike.User;
import org.mike.gui.components.ContactButton;
import org.mike.gui.content.ContactView;

import javax.swing.*;

public class AddContactDialog extends JDialog {
    private JTextField name;
    private JTextField ip;

    public AddContactDialog() {
        this.setLayout(null);
        this.setSize(300, 300);
        this.initText();
        this.initButtons();
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    private void initText() {
        final JLabel nameLabel = new JLabel("Contact name:");
        final JLabel ipLabel = new JLabel("IP:");

        this.name = new JTextField();
        this.ip = new JTextField();

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
        final JButton done = new JButton("Done");
        final JButton cancel = new JButton("Cancel");

        this.add(done);
        this.add(cancel);

        done.setBounds(100, 180, 100, 20);
        cancel.setBounds(100, 200, 100, 20);

        done.addActionListener(evt -> {
            Contact newContact = new Contact(name.getText(), ip.getText());
            ContactView cview = ContactView.getInstance();

            User.getUser().appendContactToFile(newContact);
            cview.add(new ContactButton(newContact.getName()));
            cview.revalidate();

            this.dispose();
        });

        cancel.addActionListener(evt -> this.dispose());
    }
}
