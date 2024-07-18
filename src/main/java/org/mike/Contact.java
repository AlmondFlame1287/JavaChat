package org.mike;

import org.mike.common.Constants;
import org.mike.gui.Drawable;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Contact implements Drawable {
    // TODO: Add a bio
    // TODO: Add profile picture support

    private String name;
    private String userIP;
    private File messageFile;

    private Rectangle rectangle;
//    private Image profilePicture;
    public static int latestRectangleYPos = 20;

    public Contact(String name, String userIP) {
        this.name = name;
        this.userIP = userIP;
        this.createContactFile();
        rectangle = new Rectangle(10, latestRectangleYPos, 400, 60);
        latestRectangleYPos += 60;
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

    @Override
    public void draw(Graphics g) {
        g.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        g.drawString(this.name, rectangle.x+10, rectangle.y+20);
        g.drawString(this.userIP, rectangle.x+10, rectangle.y+40);
    }

    @Override
    public String toString() {
        return this.name + "," + this.userIP;
    }
}
