package org.mike.utils;

import org.mike.Contact;
import org.mike.Message;

import java.io.*;
import java.util.logging.Logger;

public class MessageSaver {
    private static MessageSaver instance = null;
    private static final Logger logger = Logger.getLogger("MessageSaverLogger");
    private static BufferedWriter writer;


    private MessageSaver() {}

    public static MessageSaver getInstance() {
        if(instance == null) {
            instance = new MessageSaver();
        }
        return instance;
    }

    private BufferedWriter createWriter(File contactFile) {
        try {
            logger.info("Contact file: " + contactFile.toPath());
            return new BufferedWriter(new FileWriter(contactFile, true));
        } catch (IOException ioe) {
            logger.severe("There was a problem creating the writer: " + ioe.getMessage());
            return null;
        }
    }

    public void saveMessage(Message msg) {
        try {
            writer.write(msg.toString());
            writer.newLine();
            writer.flush();

            logger.info("Message saved: " + msg);
        } catch (IOException ioe) {
            logger.warning("Writer couldn't write message to file: " + ioe.getMessage());
            this.clearWriter();
        }
    }

    public void clearWriter() {
        if(writer == null) return;

        try {
            writer.close();
            logger.info("Disposed of stream");
        } catch (IOException ioe) {
            logger.warning("Something went wrong with the writer clearing: " + ioe.getMessage());
        }
    }

    public void setNewContact(Contact contact) {
        final File f = contact.getMessageFile();

        if(writer != null) {
            this.clearWriter();
        }

        writer = this.createWriter(f);
    }
}
