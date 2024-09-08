package org.mike;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.mike.common.Constants.CHAT_PATH;

public class User {
    private static User instance;
    private String name;
    private Image profilePicture;
    private String ipAddress;
    private File userFile;
    private File profilePictureFile;

    private User() {
    }

    public static User getUser() {
        if(instance == null)
            instance = new User();
        return instance;
    }

    public void createUserFile() {
        final String stringedPath = CHAT_PATH.toString() + File.separatorChar + this.name + ".txt";
        this.userFile = new File(stringedPath);

        if(userFile.exists())
            return;

        try {
            userFile.createNewFile();
        } catch(IOException ioe) {
            System.err.println("There was a problem creating the user file: " + ioe.getMessage());
        }
    }

    public void appendContactToFile(Contact toAppend) {
        try(FileWriter fw = new FileWriter(userFile, true); BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(toAppend.toString() + "\n");
        } catch(IOException ioe) {
            System.err.println("There was a problem appending a contact in your file: " + ioe.getMessage());
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void setProfilePicture(Image profilePicture, File profilePictureFile) {
        this.profilePicture = profilePicture;
        this.profilePictureFile = profilePictureFile;
    }

    public String getName() {
        return name;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public File getUserFile() {
        return userFile;
    }

    public File getProfilePictureFile() {
        return profilePictureFile;
    }

    public Image getProfilePicture() {
        return this.profilePicture;
    }
}
