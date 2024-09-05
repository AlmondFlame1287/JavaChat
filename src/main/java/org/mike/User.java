package org.mike;

import org.mike.common.Constants;

import java.awt.*;
import java.io.*;

public class User {
    private static User instance;
    private String name;
    private Image profilePicture;
    private String ipAddress;
    private File userFile;

    private User() {
    }

    public static User getUser() {
        if(instance == null)
            instance = new User();
        return instance;
    }

    public void createUserFile() {
        this.userFile = new File(Constants.CHAT_PATH + this.name + ".txt");

        if(userFile.exists())
            return;

        try {
            new File(Constants.CHAT_PATH).mkdirs();
            userFile.createNewFile();
        } catch(IOException ioe) {
            System.err.println("There was a problem creating the user file: " + ioe.getMessage());
        }
    }

    public void appendContactToFile(Contact toAppend) {
        try(FileWriter fw = new FileWriter(userFile, true); BufferedWriter bw = new BufferedWriter(fw);) {
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

    public void setProfilePicture(Image profilePicture) {
        this.profilePicture = profilePicture;
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
}
