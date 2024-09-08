package org.mike.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import static org.mike.common.Constants.*;

public class ChatFrame extends JFrame {
    public ChatFrame() {
        this.setSize(COMMON_WIDTH, COMMON_HEIGHT);
        this.setResizable(false);
        this.setContentPane(new LoginPane(this));
        this.setLocationRelativeTo(null);
        this.setTitle("JavaChat v" + VERSION);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setupIcon();
        this.setVisible(true);
    }

    private void setupIcon() {
        final String stringedIconPath = CHAT_PATH.toString() + File.separatorChar + "icon.jpg";
        final File icon = new File(stringedIconPath);
        try {
            if(icon.exists()) {
                this.setIconImage(ImageIO.read(icon));
                return;
            }
            icon.createNewFile();

            BufferedImage image = ImageIO.read(new URL("https://i.ibb.co/3Mphqtv/icon.jpg"));
            ImageIO.write(image, "jpg", icon);
            this.setIconImage(ImageIO.read(icon));
        } catch (IOException ignored) {}
    }
}
