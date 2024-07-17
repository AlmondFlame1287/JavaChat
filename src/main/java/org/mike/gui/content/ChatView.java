package org.mike.gui.content;

import org.mike.Contact;
import org.mike.gui.components.ContactArea;
import org.mike.gui.components.MessageArea;
import org.mike.gui.components.TextArea;

import javax.swing.*;


public class ChatView extends JPanel {
    private final ContactArea contactArea;
    private final MessageArea messageArea;
    private final TextArea textArea;


    public ChatView() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.contactArea = ContactArea.getInstance();
        this.messageArea = MessageArea.getInstance();
        this.textArea = new TextArea();

        this.addAreas();
    }

    private void addAreas() {
        this.add(contactArea);
        this.add(messageArea);
        this.add(textArea);
    }
}
