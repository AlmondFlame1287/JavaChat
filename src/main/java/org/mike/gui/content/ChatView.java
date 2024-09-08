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
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.contactArea = ContactArea.getInstance();
        this.messageArea = MessageArea.getInstance();
        this.textArea = new TextArea();

        this.addAreas();
    }

    private void addAreas() {
        this.add(contactArea);

        final JScrollPane scrollMessageArea = new JScrollPane(messageArea);
        this.add(scrollMessageArea);
        scrollMessageArea.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollMessageArea.setVerticalScrollBar(new CustomScrollBar());

        this.add(textArea);
    }
}
