package org.mike.gui.components.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CustomProfileButton extends JButton implements MouseListener {
    private final Color hoverBackgroundColor;
    private final Color pressedBackgroundColor;

    public CustomProfileButton(String text) {
        super(text);
        this.setOpaque(false);

        this.setBackground(new Color(52, 152, 219)); // Light blue
        this.setForeground(Color.WHITE);             // Text color
        this.hoverBackgroundColor = new Color(41, 128, 185); // Darker blue on hover
        this.pressedBackgroundColor = new Color(31, 97, 141); // Even darker blue when pressed

        this.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        this.setFocusPainted(false);

        this.addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        final Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30); // Rounded corners (30 radius)

        final FontMetrics fm = g2.getFontMetrics();
        final Rectangle textBounds = fm.getStringBounds(getText(), g2).getBounds();
        int textX = (this.getWidth() - textBounds.width) / 2;
        int textY = (this.getHeight() - textBounds.height) / 2 + fm.getAscent();
        g2.setColor(getForeground());
        g2.drawString(getText(), textX, textY);

        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
    }

    @Override
    public void setContentAreaFilled(boolean b) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setBackground(hoverBackgroundColor);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setBackground(new Color(52, 152, 219)); // Reset to original color
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.setBackground(pressedBackgroundColor);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.setBackground(hoverBackgroundColor);
    }
}
