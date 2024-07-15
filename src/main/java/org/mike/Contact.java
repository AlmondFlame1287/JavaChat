package org.mike;

//import java.awt.*;

import org.mike.gui.Drawable;

import java.awt.*;

public class Contact implements Drawable {
    // TODO: Add a bio
    // TODO: Add profile picture support

    private String name;
    private String userIP;

    private final Rectangle rectangle;
//    private Image profilePicture;

    public Contact(String name, String userIP) {
        this.name = name;
        this.userIP = userIP;
        this.rectangle = new Rectangle(10, 70, 400, 60);
    }

    public String getName() {
        return name;
    }

    public String getUserIP() {
        return userIP;
    }

    @Override
    public void draw(Graphics g) {
        g.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        rectangle.y += 20;
        g.drawString(this.name, rectangle.x, rectangle.y);
        rectangle.y += 30;
        g.drawString(this.getUserIP(), rectangle.x, rectangle.y);
    }
}
