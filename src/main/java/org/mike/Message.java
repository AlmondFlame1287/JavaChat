package org.mike;

import org.mike.gui.Drawable;

import java.awt.*;
import java.util.Date;

public class Message implements Drawable {
    public static final int MESSAGE_CHARACTER_LIMIT = 300;

    private final String message;
    private final Date dateTime;
    private final String sender;
    private final Rectangle drawingRectangle;

    public Message(Date dateTime, String message, String sender) {
        this.dateTime = dateTime;
        this.message = message;
        this.sender = sender;
        this.drawingRectangle = defineRectangleSize();
    }

    private Rectangle defineRectangleSize() {
        // TODO: This is only the default message rectangle. Make it resize itself for longer messages
        return new Rectangle(10, 70, 400, 60);
    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public String toString() {
        return this.dateTime + "," + this.message + "," + this.sender;
    }
}
