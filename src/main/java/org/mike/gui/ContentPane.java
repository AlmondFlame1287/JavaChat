package org.mike.gui;

import org.mike.User;
import org.mike.gui.content.ChatView;
import org.mike.gui.content.ContactView;

import javax.swing.*;

public class ContentPane extends JSplitPane {
    private ChatView chatView;
    private ContactView contactView;
    private JScrollPane scrollPane;

    public ContentPane() {
        chatView = new ChatView();
        contactView = ContactView.getInstance();
        scrollPane = new JScrollPane(contactView);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        this.setLeftComponent(scrollPane);
        this.setRightComponent(chatView);

        this.setDividerLocation(427);
        this.setEnabled(false);
    }
}
