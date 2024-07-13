package org.mike;

import javax.swing.*;
import org.mike.gui.ChatFrame;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChatFrame::new);
    }
}