package org.mike.gui.content;

import org.mike.User;
import org.mike.gui.AddContactDialog;
import org.mike.gui.SettingsDialog;

import javax.swing.*;
import java.awt.*;

import static org.mike.common.Constants.*;

public class ProfileView extends JPanel {
    private static ProfileView instance = null;

    private ProfileView() {
//        this.setSize(new Dimension(LEFT_COMPONENTS_WIDTH, COMMON_HEIGHT / HEIGHT_DIVIDE_FACTOR));
//        this.setLayout(null);
//        this.setBorder(BorderFactory.createEtchedBorder());
//        this.displayUsername();
//        this.displayButtons();
        this.init();
        this.displayUserImage();
        this.displayUsername();
        this.displayButtons();
    }

    private void init() {
        this.setPreferredSize(new Dimension(LEFT_VIEW_WIDTH, FRAME_HEIGHT / LEFT_VIEW_HEIGHT_DIVIDE_FACTOR));
        this.setLayout(new GridLayout(2,2));
        this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
    }

    public void displayUsername() {
        final JLabel username = new JLabel(User.getUser().getName());
        this.add(username);
    }

    public void displayUserImage() {
        final Image userPfp = User.getUser().getProfilePicture();
        if(userPfp == null) {
            // TODO: Draw a simple user image
            // For now just have a white box instead
            this.add(Box.createVerticalBox());
            return;
        }

//        final int w = this.getSize().width / WIDTH_DIVIDE_FACTOR;
//        final int h = this.getSize().height - 3;

//        final Image scaledPfp = userPfp.getScaledInstance(w, h, Image.SCALE_SMOOTH);
//        this.getGraphics().drawImage(scaledPfp, 0, 1, null);
        final ImageIcon img = new ImageIcon(userPfp);

        userPfp.flush();
//        scaledPfp.flush();

        JButton pfp = new JButton(img);
        pfp.setOpaque(false);
        pfp.setContentAreaFilled(false);
        pfp.setBorderPainted(false);

        this.add(pfp);
    }

    private void displayButtons() {
        final JButton addContact = new JButton("Add");
        final JButton settings = new JButton("Options");

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
