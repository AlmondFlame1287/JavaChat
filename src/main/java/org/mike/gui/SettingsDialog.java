package org.mike.gui;

import org.mike.User;
import org.mike.gui.content.ProfileView;

import static org.mike.common.Constants.USER_DIR;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SettingsDialog extends JDialog {
    private static Image img;
    private static File imgFile;

    public SettingsDialog() {
        this.setLayout(null);
        this.setSize(640, 480);
        this.initText();
        this.initButtons();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    private void initText() {
        final JLabel profilePictureLabel = new JLabel("Profile picture selection");

        profilePictureLabel.setBounds(10, 10, 200, 30);

        this.add(profilePictureLabel);
    }

    private void initButtons() {
        final JButton chooseFile = new JButton("Choose file..");
        final JButton done = new JButton("Done");

        chooseFile.setBounds(10, 350, 150, 30);
        done.setBounds(161, 350, 150, 30);

        chooseFile.addActionListener(evt -> this.chooseFilePressed());
        done.addActionListener(evt -> {
            User.getUser().setProfilePicture(img, imgFile);
            ProfileView.getInstance().displayUserImage();
            this.dispose();
        });

        this.add(chooseFile);
        this.add(done);
    }

    private void chooseFilePressed() {
        JFileChooser jfc = new JFileChooser(USER_DIR);
        final int result = jfc.showOpenDialog(this);

        if(result != JFileChooser.APPROVE_OPTION) return;

        try {
            imgFile = jfc.getSelectedFile();
            BufferedImage imgRead = ImageIO.read(imgFile);
            img = imgRead.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            this.getGraphics().drawImage(img, 10, 70, null);
        } catch (IOException ignored) {}
    }
}
