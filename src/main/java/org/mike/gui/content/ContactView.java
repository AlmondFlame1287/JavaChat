package org.mike.gui.content;

import org.mike.Contact;
import org.mike.User;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ContactView extends JPanel {
    private static ContactView instance;
    private Contact currentContact;

    private ArrayList<Contact> contacts;

    private ContactView() {
        this.setLayout(new GridLayout(24, 1));
        this.setPreferredSize(new Dimension(427, 720));
        this.contacts = readContacts();
        this.addContacts();
    }

    public static ContactView getInstance() {
        if(instance == null)
            instance = new ContactView();
        return instance;
    }

    private ArrayList<Contact> readContacts() {
        User currentUser = User.getUser();

        if(!currentUser.getUserFile().exists())
            return null;

        ArrayList<Contact> contactArrayList = new ArrayList<>();

        try(FileReader fr = new FileReader(currentUser.getUserFile()); BufferedReader br = new BufferedReader(fr)) {
            String line;
            while((line = br.readLine()) != null) {
                String[] split = line.split(",");
                contactArrayList.add(new Contact(split[0], split[1]));
            }
        } catch(IOException ioe) {
            System.err.println("There was a problem reading your contacts: " + ioe.getMessage());
            return null;
        }

        return contactArrayList;
    }

    private void addContacts() {
        for(Contact c : this.contacts) {
            this.add(new JButton(c.toString()));
        }

        this.revalidate();
    }
}
