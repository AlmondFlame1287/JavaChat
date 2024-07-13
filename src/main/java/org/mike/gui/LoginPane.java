package org.mike.gui;

import javax.swing.*;

public class LoginPane extends JPanel {
    private final JFrame parentClass;

    public LoginPane(JFrame parentClass) {
        this.parentClass = parentClass;
        this.setLayout(null);
        this.initTextFields();
        this.initButtons();
    }

    private void initTextFields() {
        JLabel profile = new JLabel("Profile name:");
        JLabel ip = new JLabel("IP Address:");

        JTextField profileName = new JTextField();
        JTextField ipAddress = new JTextField();

        this.add(profile);
        this.add(profileName);
        this.add(ip);
        this.add(ipAddress);

        profile.setBounds(540, 180, 100, 20);
        profileName.setBounds(540, 200, 200, 20);

        ip.setBounds(540, 220, 100, 20);
        ipAddress.setBounds(540, 240, 200, 20);
    }

    private void initButtons() {
        JButton done = new JButton("Done");
        JButton cancel = new JButton("Cancel");

        this.add(done);
        this.add(cancel);

        done.setBounds(540, 260, 100, 20);
        cancel.setBounds(640, 260, 100, 20);

        done.addActionListener(evt -> this.onDone());
        cancel.addActionListener(evt -> System.exit(0));
    }

    private void onDone() {
        // TODO: Load contacts from a save file
        this.parentClass.setContentPane(new ContentPane());
        this.parentClass.revalidate();
    }
}
