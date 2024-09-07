package org.mike.gui.components.ui;

import javax.swing.*;
import java.awt.*;

public class CustomTextField extends JTextField {
    private final int radius;
    private final Color backgroundColor;
    private final Color borderColor;

    public CustomTextField() {
        super();
        this.radius = 20;
        this.backgroundColor = Color.WHITE;
        this.borderColor = new Color(52, 152, 219);

        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        setOpaque(false); // Make sure we handle the background painting ourselves
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(backgroundColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

        super.paintComponent(g);

        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(2));

        g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, radius, radius);

        g2.dispose();
    }

    @Override
    public void setOpaque(boolean isOpaque) {
        super.setOpaque(false);
    }
}
