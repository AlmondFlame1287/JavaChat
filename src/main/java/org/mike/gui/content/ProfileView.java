package org.mike.gui.content;

import org.mike.User;

import javax.swing.*;
import java.awt.*;

public class ProfileView extends JPanel {
    private static ProfileView instance = null;

    private ProfileView() {
        this.setPreferredSize(new Dimension(427, 100));
        this.setLayout(null);
        this.setBorder(BorderFactory.createEtchedBorder());
        this.displayUsername();
    }

    public void displayUsername() {
        final JLabel username = new JLabel(User.getUser().getName());
        username.setBounds(130, 35, 100, 30);
        this.add(username);
    }

    public void displayUserImage() {
        final Image userPfp = User.getUser().getProfilePicture().getScaledInstance(100, 96, Image.SCALE_SMOOTH);
        if(userPfp == null) return;
        this.getGraphics().drawImage(userPfp, 0, 2, null);
    }

    public static ProfileView getInstance() {
        if(instance == null)
            instance = new ProfileView();
        return instance;
    }
}
