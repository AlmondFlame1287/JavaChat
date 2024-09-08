package org.mike.gui.content;

import org.mike.User;
import org.mike.gui.AddContactDialog;
import org.mike.gui.SettingsDialog;
import org.mike.gui.components.customs.CustomButton;

import javax.swing.*;
import java.awt.*;

import static org.mike.common.Constants.*;

public class ProfileView extends JPanel {
    private static ProfileView instance = null;

    private ProfileView() {
        this.setPreferredSize(new Dimension(LEFT_COMPONENTS_WIDTH, COMMON_HEIGHT / HEIGHT_DIVIDE_FACTOR));
        this.setLayout(null);
        this.setBorder(BorderFactory.createEtchedBorder());
        this.displayUsername();
        this.displayButtons();
    }

    public void displayUsername() {
        final JLabel username = new JLabel(User.getUser().getName());

        final int w = 100;
        final int h = 30;
        final int x = this.getPreferredSize().width / 2 - (w / 4);
        final int y = this.getPreferredSize().height / 2 - (h / 2);

        username.setBounds(x, y, w, h);
        this.add(username);
    }

    public void displayUserImage() {
        final Image userPfp = User.getUser().getProfilePicture();
        if(userPfp == null) return;

        final int w = this.getPreferredSize().width / WIDTH_DIVIDE_FACTOR;
        final int h = this.getPreferredSize().height - 2;

        final Image scaledPfp = userPfp.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        this.getGraphics().drawImage(scaledPfp, 0, 0, null);
    }

    private void displayButtons() {
        final CustomButton addContact = new CustomButton("Add");
        final CustomButton settings = new CustomButton("Options");

        final int buttonW = 70;
        final int buttonH = 30;
        final int buttonX = this.getPreferredSize().width / 3 + buttonW;

        final int addContactY = this.getPreferredSize().height / 2 - buttonH;
        final int settingsY = addContactY + buttonH;

        addContact.setBounds(buttonX, addContactY, buttonW, buttonH);
        settings.setBounds(buttonX, settingsY, buttonW, buttonH);

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
