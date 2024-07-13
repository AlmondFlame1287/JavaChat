package org.mike.gui;

import org.mike.gui.content.ChatView;
import org.mike.gui.content.ContactView;

import javax.swing.*;

public class ContentPane extends JSplitPane {
    private ChatView chatView = new ChatView();
    private ContactView contactView = new ContactView();

    public ContentPane() {
        this.setLeftComponent(contactView);
        this.setRightComponent(chatView);

        this.setDividerLocation(427);
        this.setEnabled(false);
    }
}
