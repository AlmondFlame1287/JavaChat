package org.mike.utils;

import org.mike.Contact;
import org.mike.Message;

import java.io.*;
import java.util.logging.Logger;

public class MessageSaver {
    private static MessageSaver instance = null;
    private static final Logger logger = Logger.getLogger("MessageSaverLogger");
    private static BufferedWriter writer;
    private static String fileName;


    private MessageSaver(Contact contact) {
        writer = this.createWriter(contact.getMessageFile());
    }

    public static MessageSaver getInstanceForContact(Contact contact) {
        if(instance == null) {
            instance = new MessageSaver(contact);
        } else if(!fileName.equals(contact.getMessageFile().getName())) {
            instance.dispose();
            instance = new MessageSaver(contact);
        }
        return instance;
    }

    private BufferedWriter createWriter(File contactFile) {
        try {
            fileName = contactFile.getName();
            return new BufferedWriter(new FileWriter(contactFile));
        } catch (IOException ioe) {
            logger.severe("There was a problem creating the writer: " + ioe.getMessage());
            return null;
        }
    }

    public void saveMessageToFile(File file, Message msg) {
        try {
            if(!file.exists()) {
                throw new FileNotFoundException();
            }

            writer.write(msg.toString() + "\n");
        } catch (FileNotFoundException fnfe) {
            logger.severe("Couldn't find filea at " + file.toPath());
        } catch (IOException ioe) {
            logger.warning("Writer couldn't write message to file: " + ioe.getMessage());
        } finally {
            this.dispose();
        }
    }

    public void dispose() {
        if(writer == null) return;

        try {
            writer.close();
            logger.info("Disposed of stream for file: " + fileName);
        } catch (IOException ioe) {
            logger.warning("Something went wrong with the disposla: " + ioe.getMessage());
        }
    }
}
