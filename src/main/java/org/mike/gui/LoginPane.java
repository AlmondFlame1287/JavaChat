package org.mike.gui;

import org.mike.User;
import org.mike.connection.Client;
import org.mike.connection.Server;

import javax.swing.*;

public class LoginPane extends JPanel {
    // TODO: Remove userip
    private static Server serv;
    private static Client client;

    private JTextField profileName;
    private JTextField ipAddress;
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

        profileName = new JTextField();
        ipAddress = new JTextField();

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
        User user = User.getUser();
        user.setName(profileName.getText());
        user.setIpAddress(ipAddress.getText());
        user.createUserFile();

        serv = new Server();
        Thread serverThread = new Thread(serv);
        serverThread.start();

        client = new Client();

        this.parentClass.setContentPane(new ContentPane());
        this.parentClass.revalidate();
    }

    public static Server getServer() {
        return serv;
    }

    public static Client getClient() {
        return client;
    }
}
