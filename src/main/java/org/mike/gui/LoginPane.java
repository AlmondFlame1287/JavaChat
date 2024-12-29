package org.mike.gui;

import org.mike.User;
import org.mike.connection.Server;
import org.mike.gui.components.customs.CustomButton;
import org.mike.gui.components.customs.CustomTextField;

import javax.swing.*;
import java.awt.*;

public class LoginPane extends JPanel {
    private CustomTextField profileName;
    private final JFrame parentClass;

    public LoginPane(JFrame parentClass) {
        this.parentClass = parentClass;
        this.setLayout(new GridBagLayout());
        this.initComponents();
        this.initServerThread();
    }

    private void initComponents() {
        final JLabel usernameLabel = new JLabel("Username:");
        final CustomButton done = new CustomButton("Done");
        final CustomButton exit = new CustomButton("Exit");
        final CustomButton lightweightMode = new CustomButton("LightWeight mode");
        profileName = new CustomTextField();

        GridBagConstraints c = new GridBagConstraints();

        done.addActionListener(evt -> onDone());
        exit.addActionListener(evt -> System.exit(0));
//        lightweightMode.addActionListener();

        c.insets = new Insets(0, 1, 0, 1);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = c.gridy = 0;
        this.add(usernameLabel, c);

        c.gridy = 1;
        c.gridwidth = 4;
        this.add(profileName, c);

        c.gridy = 2;
        c.gridwidth = 1;
        this.add(done, c);

        c.gridx = 2;
        this.add(exit, c);
        c.gridx = 3;
        this.add(lightweightMode, c);
    }

    private void initServerThread() {
        new Thread(Server.getInstance()).start();
    }

    private void onDone() {
        User user = User.getUser();
        user.setName(profileName.getText());
        user.createUserFile();

        this.parentClass.setContentPane(new ContentPane());
        this.parentClass.revalidate();
    }
}
