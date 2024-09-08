package org.mike;

import org.mike.gui.ChatFrame;
import org.mike.utils.DirectoryInitializer;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        DirectoryInitializer.initializeDirectories();
        SwingUtilities.invokeLater(ChatFrame::new);
    }
}