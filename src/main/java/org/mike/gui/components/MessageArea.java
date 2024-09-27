package org.mike.gui.components;

import org.mike.Message;
import org.mike.utils.MessageSaver;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.mike.common.Constants.*;

public class MessageArea extends JPanel {
    private static MessageArea instance = null;
    private static final Logger logger = Logger.getLogger("MessageAreaLogger");
    private ArrayList<Message> messages;

    private MessageArea() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setPreferredSize(new Dimension(RIGHT_COMPONENTS_WIDTH, (COMMON_HEIGHT / HEIGHT_DIVIDE_FACTOR) * 5));
    }

    public static MessageArea getInstance() {
        if(instance == null)
            instance = new MessageArea();
        return instance;
    }

    public void addMessage(Message msg) {
        this.messages.add(msg);
        this.add(msg.getMessageLabel());
        MessageSaver.getInstance().saveMessage(msg);

        this.revalidate();
    }

    public void drawMessages() {
        if(messages == null) return;

        for(Message msg : messages) {
            this.add(msg.getMessageLabel());
        }

        this.revalidate();
    }

    public void loadMessagesFromFile(File f) {
        if(!f.exists()) return;

        ArrayList<Message> result = new ArrayList<>();

        try(FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr)) {

            String line;

            while((line = br.readLine()) != null) {
                result.add(stringToMessage(line));
            }
        } catch (IOException ioe) {
            logger.severe("Something went wrong with loading the file: " + ioe.getMessage());
        }

        this.messages = result;
    }

    public static Message stringToMessage(String str) {
        final Pattern pattern = Pattern.compile("(\\d{2}/\\d{2}/\\d{4}, \\d{2}:\\d{2}) \\| (\\w+): (.+)");
        final Matcher matcher = pattern.matcher(str);

        if(!matcher.find()) {
            throw new RuntimeException("Couldn't find anything");
        }

        return new Message(LocalDateTime.parse(matcher.group(1), DATE_TIME_FORMATTER), matcher.group(3), matcher.group(2));
    }

    public void clear() {
        this.removeAll();

        this.repaint();
        this.revalidate();
    }
}
