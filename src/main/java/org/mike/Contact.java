package org.mike;

import org.mike.gui.Drawable;

import java.awt.*;

public class Contact implements Drawable {
    // TODO: Add a bio
    // TODO: Add profile picture support

    private String name;
    private String userIP;

    private static final Rectangle rectangle = new Rectangle(10, 70, 400, 60);
//    private Image profilePicture;

    public Contact(String name, String userIP) {
        this.name = name;
        this.userIP = userIP;
    }

    public String getName() {
        return name;
    }

    public String getUserIP() {
        return userIP;
    }

    public static Rectangle getRectangle() {
        return rectangle;
    }

    @Override
    public void draw(Graphics g) {
        g.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        g.drawString(this.name, rectangle.x+10, rectangle.y+20);
        g.drawString(this.userIP, rectangle.x+10, rectangle.y+40);
        rectangle.y += 60;
    }

    @Override
    public String toString() {
        return this.name + "," + this.userIP;
    }
}
