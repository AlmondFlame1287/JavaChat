package org.mike.gui.content;

import org.mike.Contact;
import org.mike.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ContactView extends JPanel implements MouseListener {
    private static ContactView instance;

    private ArrayList<Contact> contacts;

    private ContactView() {
        this.contacts = this.readContacts();
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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.drawContacts(g);
    }

    private void drawContacts(Graphics g) {
        if(contacts == null)
            return;

        for(Contact cont : contacts) {
            cont.draw(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
