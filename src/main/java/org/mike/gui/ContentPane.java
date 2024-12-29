package org.mike.gui;

import org.mike.gui.components.customs.CustomScrollBar;
import org.mike.gui.content.RightView;
import org.mike.gui.content.LeftView;

import javax.swing.*;

import static org.mike.common.Constants.LEFT_VIEW_WIDTH;

public class ContentPane extends JSplitPane {
    public ContentPane() {
        final RightView rightView = new RightView();
        final LeftView leftView = LeftView.getInstance();
        final JScrollPane scrollPane = new JScrollPane(leftView);
        scrollPane.setVerticalScrollBar(new CustomScrollBar());
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        this.setLeftComponent(scrollPane);
        this.setRightComponent(rightView);

        this.setDividerLocation(LEFT_VIEW_WIDTH);
        this.setEnabled(false);
    }
}
