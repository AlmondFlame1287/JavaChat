package org.mike;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static org.mike.common.Constants.CONTACT_MESSAGES_PATH;

public class Contact {
    // TODO: Add a bio

    private String name;
    private String userIP;
    private File messageFile;

    private Image profilePicture;

    public Contact(String name, String userIP) {
        this.name = name;
        this.userIP = userIP;
        this.createContactFile();
    }

    public void loadProfilePicture() {
        final String stringedPfpFilePath = CONTACT_MESSAGES_PATH.toString() + File.separatorChar + this.name +".jpg";
        final File pfpFile = new File(stringedPfpFilePath);

        try {
            if(!pfpFile.exists()) { // If the named pfp file doesn't exist then just get the temp pfp
                this.profilePicture = ImageIO.read(new File(stringedPfpFilePath.replace(this.name, "pfp")));
                return;
            }

            this.profilePicture = ImageIO.read(pfpFile);
        } catch (IOException ignored) {}
    }

    public String getName() {
        return name;
    }

    public String getUserIP() {
        return userIP;
    }

    public void createContactFile() {
        final String stringedMessageFilePath = CONTACT_MESSAGES_PATH.toString() + File.separatorChar + "to" + this.name + ".txt";
        this.messageFile = new File(stringedMessageFilePath);

        if(messageFile.exists())
            return;

        try {
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
