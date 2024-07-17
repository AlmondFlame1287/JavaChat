package org.mike.gui.content;

import org.mike.Contact;
import org.mike.User;
import org.mike.gui.components.ContactArea;
import org.mike.gui.components.MessageArea;

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
    private Contact currentContact;

    private ArrayList<Contact> contacts;

    private ContactView() {
        this.addMouseListener(this);
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

    public Contact getPressedContact() {
        return this.currentContact;
    }

    private Contact checkContactClicked(Point cursorPos) {
        for(Contact c : contacts) {
            if(c.getRectangle().contains(cursorPos)) {
                System.out.println("Contact Clicked: " + c.getName());
                return c;
            }
        }

        return null;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO: Return clickedContact to ContactArea
        Point cursorPos = new Point(e.getX(), e.getY());
        this.currentContact = checkContactClicked(cursorPos);
        MessageArea.getInstance().readMessages();

        ContactArea.getInstance().updateArea();
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
