package org.mike.gui.components.customs;

import javax.swing.*;
import java.awt.*;

public class CustomScrollBar extends JScrollBar {
    public CustomScrollBar() {
        setUI(new ModernScrollbarUI());
        setPreferredSize(new Dimension(10, Integer.MAX_VALUE));
        setOpaque(false);
    }
}
