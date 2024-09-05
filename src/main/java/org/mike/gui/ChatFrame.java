package org.mike.gui;

import org.mike.User;
import static org.mike.common.Constants.VERSION;
import static org.mike.common.Constants.CHAT_PATH;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ChatFrame extends JFrame {
    public ChatFrame() {
        this.setSize(1280, 720);
        this.setResizable(false);
        this.setContentPane(new LoginPane(this));
        this.setLocationRelativeTo(null);
        this.setTitle("JavaChat v" + VERSION);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setupIcon();
        this.setupMenuBar();
        this.setVisible(true);
    }

    private void setupIcon() {
        File icon = new File(CHAT_PATH + "icon.jpg");
        try {
            if(icon.exists()) {
                this.setIconImage(ImageIO.read(icon));
                return;
            }

            BufferedImage image = ImageIO.read(new URL("https://i.ibb.co/3Mphqtv/icon.jpg"));
            ImageIO.write(image, "jpg", icon);
            this.setIconImage(ImageIO.read(icon));
        } catch (IOException ignored) {}
    }

    private void setupMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu contactsMenu = new JMenu("Contacts");
        JMenuItem addContact = new JMenuItem("Add");

        menuBar.add(contactsMenu);
        contactsMenu.add(addContact);

        this.setJMenuBar(menuBar);
        addContact.addActionListener(evt -> this.addContactPressed());
    }

    private void addContactPressed() {
        if(User.getUser().getName() == null)
            return;

        new AddContactDialog();
    }
}
