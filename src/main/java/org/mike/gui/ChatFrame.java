package org.mike.gui;

import org.mike.User;

import javax.swing.*;

public class ChatFrame extends JFrame {
    public ChatFrame() {
        this.setSize(1280, 720);
        this.setResizable(false);
        this.setContentPane(new LoginPane(this));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setupMenuBar();
        this.setVisible(true);
    }

    private void setupMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu contactsMenu = new JMenu("Contacts");
        JMenuItem addContact = new JMenuItem("Add");

        menuBar.add(contactsMenu);
        contactsMenu.add(addContact);

        this.setJMenuBar(menuBar);
        addContact.addActionListener(evt -> this.addContactPressed());
    }

    private void addContactPressed() {
        if(User.getUser().getName() == null)
            return;

        new AddContactDialog();
    }
}
