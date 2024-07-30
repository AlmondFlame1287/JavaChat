package org.mike.gui.content;

import org.mike.gui.components.ContactArea;
import org.mike.gui.components.MessageArea;
import org.mike.gui.components.TextArea;

import javax.swing.*;


public class ChatView extends JPanel {
    private final ContactArea contactArea;
    private final MessageArea messageArea;
    private final TextArea textArea;


    public ChatView() {
        this.setLayout(null);
        this.contactArea = ContactArea.getInstance();
        this.messageArea = MessageArea.getInstance();
        this.textArea = new TextArea();

        this.addAreas();
    }

    private void addAreas() {
        this.add(contactArea);
        // TODO: Add scrollpane to messageArea
        this.add(messageArea);
        this.add(textArea);

        this.contactArea.setBounds(0, 0, 853, 150);
        this.messageArea.setBounds(0, 151, 853, 420);
        this.textArea.setBounds(0, 572, 853, 150);
    }
}
