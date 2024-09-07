package org.mike.gui.components.customs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CustomButton extends JButton implements MouseListener {
    private final Color originalColor;
    private final Color hoverColor;
    private final Color pressedColor;

    public CustomButton(String text) {
        super(text);

        this.originalColor = Color.WHITE;
        this.hoverColor = Color.GRAY;
        this.pressedColor = Color.DARK_GRAY;
        this.setOpaque(false);
        this.setFocusPainted(false);
        this.setBackground(this.originalColor);
        this.setForeground(Color.BLACK);
        this.addMouseListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        final Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(this.getBackground());
        g2d.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 30, 30); // Rounded corners (30 radius)

        // Draw text in the center of the button
        final FontMetrics fm = g2d.getFontMetrics();
        final Rectangle textBounds = fm.getStringBounds(this.getText(), g2d).getBounds();
        final int textX = (this.getWidth() - textBounds.width) / 2;
        final int textY = (this.getHeight() - textBounds.height) / 2 + fm.getAscent();
        g2d.setColor(this.getForeground());
        g2d.drawString(this.getText(), textX, textY);

        g2d.dispose();
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.setBackground(this.pressedColor);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.setBackground(this.originalColor);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setBackground(this.hoverColor);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setBackground(this.originalColor);
    }

    @Override
    public void paintBorder(Graphics g) {
        final Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Color.DARK_GRAY);
        g2d.setStroke(new BasicStroke(1));

        g2d.drawRoundRect(2, 2, this.getWidth() - 4, this.getHeight() - 4, 30, 30);

        g2d.dispose();
    }

    @Override
    public void setContentAreaFilled(boolean filled) {}
}
