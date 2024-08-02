package org.mike;

import javax.swing.*;
import java.time.LocalDateTime;

public class Message /*implements Drawable*/ {
    public static final int MESSAGE_CHARACTER_LIMIT = 300;

    private String message;
    private final LocalDateTime dateTime;
    private final String sender;
    private transient JLabel messageLabel;

    public Message(LocalDateTime dateTime, String message, String sender) {
        this.dateTime = dateTime;
        this.checkMessageLength(message);
        this.sender = sender;
        this.messageLabel = new JLabel(this.message);
    }

    private void checkMessageLength(String msg) {
        if(msg.length() <= MESSAGE_CHARACTER_LIMIT) {
            this.message = msg;
            return;
        }

        this.message = msg.substring(0, MESSAGE_CHARACTER_LIMIT);
    }

    public JLabel getMessageLabel() {
        return messageLabel;
    }

    @Override
    public String toString() {
        return this.dateTime + "," + this.message + "," + this.sender;
    }
}
