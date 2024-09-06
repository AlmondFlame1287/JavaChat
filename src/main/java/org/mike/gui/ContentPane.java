package org.mike.gui;

import org.mike.gui.content.ChatView;
import org.mike.gui.content.LeftView;

import javax.swing.*;

public class ContentPane extends JSplitPane {
    public ContentPane() {
        final ChatView chatView = new ChatView();
        final LeftView leftView = LeftView.getInstance();
        final JScrollPane scrollPane = new JScrollPane(leftView);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        this.setLeftComponent(scrollPane);
        this.setRightComponent(chatView);

        this.setDividerLocation(427);
        this.setEnabled(false);
    }
}
