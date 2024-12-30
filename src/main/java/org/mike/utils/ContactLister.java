package org.mike.utils;

import org.mike.Contact;
import org.mike.User;
import org.mike.connection.Client;
import org.mike.gui.components.ContactArea;
import org.mike.gui.components.MessageArea;
import org.mike.gui.components.customs.CustomListCellRenderer;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.mike.common.Constants.COMMUNICATION_PORT;

public class ContactLister {
    private static JList<Contact> contactList;

    private ContactLister() {}

    private static void init() {
        DefaultListModel<Contact> dlm = readContacts();

        if(dlm == null) return;

        contactList = new JList<>(dlm);
        contactList.addListSelectionListener(evt -> onSelectedContact());
        contactList.setCellRenderer(new CustomListCellRenderer());
    }

    private static void onSelectedContact() {
        int index = contactList.getSelectedIndex();
        final Contact selected = contactList.getModel().getElementAt(index);

        MessageSaver.getInstance().setNewContact(selected);
        MessageArea.getInstance().loadMessagesFromFile(selected.getMessageFile());
        MessageArea.getInstance().drawMessages();
        ContactArea.getInstance().setContact(selected);
        final Client c = new Client(selected.getUserIP(), COMMUNICATION_PORT);
        new Thread(c).start();
    }

    public static void addToContacts(Contact newContact) {
        DefaultListModel<Contact> dlm = (DefaultListModel<Contact>) contactList.getModel();
        dlm.addElement(newContact);
        contactList.setModel(dlm);
    }

    private static DefaultListModel<Contact> readContacts() {
        User currentUser = User.getUser();

        if(!currentUser.getUserFile().exists())
            return null;

        DefaultListModel<Contact> contactDefaultListModel = new DefaultListModel<>();

        try(FileReader fr = new FileReader(currentUser.getUserFile()); BufferedReader br = new BufferedReader(fr)) {
            String line;

            while((line = br.readLine()) != null) {
                String[] split = line.split(",");
                contactDefaultListModel.addElement(new Contact(split[0], split[1]));
            }

        } catch(IOException ioe) {
            System.err.println("There was a problem reading your contacts: " + ioe.getMessage());
            return null;
        }

        return contactDefaultListModel;
    }

    public static JList<Contact> getContactList() {
        if(contactList == null) {
            init();
        }
        return contactList;
    }
}
