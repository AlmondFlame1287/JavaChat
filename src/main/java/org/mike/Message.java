package org.mike;

import org.mike.gui.Drawable;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.Date;

public class Message implements Drawable {
    public static final int MESSAGE_CHARACTER_LIMIT = 300;

    private final String message;
    private final LocalDateTime dateTime;
    private final String sender;
    private final Rectangle drawingRectangle;

    public Message(LocalDateTime dateTime, String message, String sender) {
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
        int width = g.getFontMetrics().stringWidth(this.message);
        Rectangle messageRectangle = new Rectangle(10, 10, width, 20);

        g.drawRect(messageRectangle.x, messageRectangle.y, messageRectangle.width, messageRectangle.height);
        g.drawString(this.message, messageRectangle.x, messageRectangle.y+10);
    }

    @Override
    public String toString() {
        return this.dateTime + "," + this.message + "," + this.sender;
    }
}
