package org.mike;

import javax.swing.*;
import java.time.LocalDateTime;
import static org.mike.common.Constants.DATE_TIME_FORMATTER;

public class Message {
    public static final int MESSAGE_CHARACTER_LIMIT = 300;

    private String message;
    private final String dateTime;
    private final String sender;

    public Message(LocalDateTime dateTime, String message, String sender) {
        this.dateTime = this.formatDateTime(dateTime);
        this.checkMessageLength(message);
        this.sender = sender;
    }

    private String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DATE_TIME_FORMATTER);
    }

    private void checkMessageLength(String msg) {
        if(msg.length() <= MESSAGE_CHARACTER_LIMIT) {
            this.message = msg;
            return;
        }

        this.message = msg.substring(0, MESSAGE_CHARACTER_LIMIT);
    }

    public JLabel getMessageLabel() {
        return new JLabel(this.toString());
    }

    public String getText() {
        return message;
    }

    @Override
    public String toString() {
        return this.dateTime + " | " + this.sender + ": " + this.message;
    }
}
