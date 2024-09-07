package org.mike.gui;

import org.mike.User;
import org.mike.connection.Server;
import org.mike.gui.components.customs.CustomButton;
import org.mike.gui.components.customs.CustomTextField;

import javax.swing.*;

public class LoginPane extends JPanel {
    private CustomTextField profileName;
    private final JFrame parentClass;

    public LoginPane(JFrame parentClass) {
        this.parentClass = parentClass;
        this.setLayout(null);
        this.initTextFields();
        this.initButtons();
        this.initServerThread();
    }

    private void initTextFields() {
        JLabel profile = new JLabel("Profile name:");
        profileName = new CustomTextField();

        this.add(profile);
        this.add(profileName);

        profile.setBounds(540, 180, 100, 20);
        profileName.setBounds(540, 200, 200, 25);
    }

    private void initButtons() {
        final CustomButton done = new CustomButton("Done");
        final CustomButton cancel = new CustomButton("Cancel");

        this.add(done);
        this.add(cancel);

        done.setBounds(540, 230, 100, 20);
        cancel.setBounds(640, 230, 100, 20);

        done.addActionListener(evt -> this.onDone());
        cancel.addActionListener(evt -> System.exit(0));
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
