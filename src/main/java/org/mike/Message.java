package org.mike;

import org.mike.gui.Drawable;

import java.awt.*;
import java.time.LocalDateTime;

public class Message implements Drawable {
    public static final int MESSAGE_CHARACTER_LIMIT = 300;

    private final String message;
    private final LocalDateTime dateTime;
    private final String sender;
    private static int latestMessageYPos = 10;

    public Message(LocalDateTime dateTime, String message, String sender) {
        this.dateTime = dateTime;
        this.message = message;
        this.sender = sender;
    }

    private Rectangle defineRectangleSize(int width) {
        Rectangle r = new Rectangle(10, latestMessageYPos, width, 30);
        latestMessageYPos += 31;
        return r;
    }

    @Override
    public void draw(Graphics g) {
        int width = g.getFontMetrics().stringWidth(this.message);
        Rectangle drawingRectangle = this.defineRectangleSize(width);

        g.drawRect(drawingRectangle.x, drawingRectangle.y, drawingRectangle.width+5, drawingRectangle.height);
        g.drawString(this.message, drawingRectangle.x+2, drawingRectangle.y+16);
    }

    @Override
    public String toString() {
        return this.dateTime + "," + this.message + "," + this.sender;
    }
}
