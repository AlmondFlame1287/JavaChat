package org.mike.gui;

import org.mike.User;
import org.mike.gui.content.ChatView;
import org.mike.gui.content.ContactView;

import javax.swing.*;

public class ContentPane extends JSplitPane {
    private ChatView chatView;
    private ContactView contactView;

    public ContentPane() {
        chatView = new ChatView();
        contactView = ContactView.getInstance();
        this.setLeftComponent(contactView);
        this.setRightComponent(chatView);

        this.setDividerLocation(427);
        this.setEnabled(false);
    }
}
