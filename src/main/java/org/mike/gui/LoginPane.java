package org.mike.gui;

import org.mike.User;
import org.mike.connection.Server;
import org.mike.gui.components.customs.CustomButton;
import org.mike.gui.components.customs.CustomTextField;

import javax.swing.*;
import java.awt.*;

import static org.mike.common.Constants.*;

public class LoginPane extends JPanel {
    private CustomTextField profileName;
    private final JFrame parentClass;
    private static int profileNameY;

    public LoginPane(JFrame parentClass) {
        this.setSize(new Dimension(COMMON_WIDTH, COMMON_HEIGHT));
        this.parentClass = parentClass;
        this.setLayout(null);
        this.initTextFields();
        this.initButtons();
        this.initServerThread();
    }

    private void initTextFields() {
        final JLabel profile = new JLabel("Profile name:");
        profileName = new CustomTextField();

        this.add(profile);
        this.add(profileName);

        final int profileW = 100;
        final int profileH = 20;
        final int profileNameW = 200;
        final int profileNameH = 30;

        final int elemX = (this.getWidth() / WIDTH_DIVIDE_FACTOR) * 2 - profileW;

        final int profileY = (this.getHeight() / 2) - profileH - (profileNameH * 2);
        profileNameY = profileY + profileH;

        profile.setBounds(elemX, profileY, profileW, profileH);
        profileName.setBounds(elemX, profileNameY, profileNameW, profileNameH);
    }

    private void initButtons() {
        final CustomButton done = new CustomButton("Done");
        final CustomButton cancel = new CustomButton("Cancel");

        this.add(done);
        this.add(cancel);

        final int buttonW = 100;
        final int buttonH = 20;
        final int buttonY = (this.getHeight() / 2) - buttonH;

        final int doneX = (this.getWidth() / 2) - buttonW;
        final int cancelX = doneX + buttonW;

        done.setBounds(doneX, buttonY, buttonW, buttonH);
        cancel.setBounds(cancelX, buttonY, buttonW, buttonH);

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
