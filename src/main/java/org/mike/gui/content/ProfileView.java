package org.mike.gui.content;

import org.mike.User;
import org.mike.gui.AddContactDialog;
import org.mike.gui.SettingsDialog;

import javax.swing.*;
import java.awt.*;

public class ProfileView extends JPanel {
    private static ProfileView instance = null;

    private ProfileView() {
        this.setPreferredSize(new Dimension(427, 100));
        this.setLayout(null);
        this.setBorder(BorderFactory.createEtchedBorder());
        this.displayUsername();
        this.displayButtons();
    }

    public void displayUsername() {
        final JLabel username = new JLabel(User.getUser().getName());
        username.setBounds(130, 35, 100, 30);
        this.add(username);
    }

    public void displayUserImage() {
        final Image userPfp = User.getUser().getProfilePicture();
        if(userPfp == null) return;

        final Image scaledPfp = userPfp.getScaledInstance(100, 96, Image.SCALE_SMOOTH);
        this.getGraphics().drawImage(scaledPfp, 0, 0, null);
    }

    private void displayButtons() {
        final JButton addContact = new JButton("Add contact");
        final JButton settings = new JButton("Settings");

        addContact.setBounds(260, 0, 150, 30);
        settings.setBounds(260, 30, 150, 30);

        this.add(addContact);
        this.add(settings);

        addContact.addActionListener(evt -> this.addContactPressed());
        settings.addActionListener(evt -> this.settingsPressed());
    }

    public static ProfileView getInstance() {
        if(instance == null)
            instance = new ProfileView();
        return instance;
    }

    private void addContactPressed() {
        if(User.getUser().getName() == null)
            return;

        SwingUtilities.invokeLater(AddContactDialog::new);
    }

    private void settingsPressed() {
        if(User.getUser().getName() == null) return;

        SwingUtilities.invokeLater(SettingsDialog::new);
    }
}
