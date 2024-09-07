package org.mike.gui.components.customs;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class ModernScrollbarUI extends BasicScrollBarUI {

    private final Color thumbColor = new Color(52, 152, 219, 180);
    private final Color trackColor = new Color(200, 200, 200, 100);

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(thumbColor);
        g2.fillRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, 10, 10); // Rounded thumb
        g2.dispose();
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(trackColor);
        g2.fillRoundRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height, 10, 10);  // Rounded track
        g2.dispose();
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return createInvisibleButton();
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return createInvisibleButton();
    }

    private JButton createInvisibleButton() {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(0, 0));
        button.setMinimumSize(new Dimension(0, 0));
        button.setMaximumSize(new Dimension(0, 0));
        return button;
    }
}
