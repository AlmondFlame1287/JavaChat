package org.mike.gui.content;

import javax.swing.*;

public class LeftView extends JPanel {
    private static LeftView instance = null;

    private LeftView() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setupPanels();
    }

    private void setupPanels() {
        final ProfileView profile = ProfileView.getInstance();
        final ContactView contactView = ContactView.getInstance();

        this.add(profile);
        this.add(contactView);
    }

    public static LeftView getInstance() {
        if(instance == null)
            instance = new LeftView();
        return instance;
    }
}
