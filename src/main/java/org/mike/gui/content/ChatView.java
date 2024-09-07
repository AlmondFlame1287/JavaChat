package org.mike.gui.content;

import org.mike.gui.components.ContactArea;
import org.mike.gui.components.MessageArea;
import org.mike.gui.components.TextArea;
import org.mike.gui.components.customs.CustomScrollBar;

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

        final JScrollPane scrollPane = new JScrollPane(messageArea);
        this.add(scrollPane);
        this.add(textArea);

        this.contactArea.setBounds(0, 0, 853, 100);
        scrollPane.setVerticalScrollBar(new CustomScrollBar());
        scrollPane.setBounds(0, 101, 824, 501);
        this.textArea.setBounds(0, 602, 853, 80);
    }
}
