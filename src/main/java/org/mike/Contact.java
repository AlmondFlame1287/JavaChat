package org.mike;

import org.mike.common.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Contact {
    // TODO: Add a bio
    // TODO: Add profile picture support

    private String name;
    private String userIP;
    private File messageFile;

    private Rectangle rectangle;
    private Image profilePicture;
    public static int latestRectangleYPos = 20;

    public Contact(String name, String userIP) {
        this.name = name;
        this.userIP = userIP;
        this.createContactFile();
        rectangle = new Rectangle(10, latestRectangleYPos, 400, 60);
        latestRectangleYPos += 60;
    }

    public void loadProfilePicture() {
        // TODO: fix this path
        final File pfpFile = new File(Constants.CHAT_PATH + "pfp.jpg");
        if(!pfpFile.exists()) return;

        try {
            this.profilePicture = ImageIO.read(pfpFile);
        } catch (IOException ignored) {}
    }


    public String getName() {
        return name;
    }

    public String getUserIP() {
        return userIP;
    }

    public Rectangle getRectangle() { return rectangle; }

    public void createContactFile() {
        this.messageFile = new File(Constants.CONTACT_MESSAGES_PATH + "to" + this.name + ".txt");

        if(messageFile.exists())
            return;

        try {
            new File(Constants.CONTACT_MESSAGES_PATH).mkdirs();
            messageFile.createNewFile();
        } catch(IOException ioe) {
            System.err.println("There was a problem creating the Contact file: " + ioe.getMessage());
        }
    }

    public File getMessageFile() {
        return messageFile;
    }

    public Image getProfilePicture() {
        return profilePicture;
    }

    @Override
    public String toString() {
        return this.name + "," + this.userIP;
    }
}
