package org.mike.gui;

import org.mike.gui.components.customs.CustomScrollBar;
import org.mike.gui.content.ChatView;
import org.mike.gui.content.LeftView;

import javax.swing.*;

import static org.mike.common.Constants.LEFT_COMPONENTS_WIDTH;

public class ContentPane extends JSplitPane {
    public ContentPane() {
        final ChatView chatView = new ChatView();
        final LeftView leftView = LeftView.getInstance();
        final JScrollPane scrollPane = new JScrollPane(leftView);
        scrollPane.setVerticalScrollBar(new CustomScrollBar());
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        this.setLeftComponent(scrollPane);
        this.setRightComponent(chatView);

        this.setDividerLocation(LEFT_COMPONENTS_WIDTH);
        this.setEnabled(false);
    }
}
