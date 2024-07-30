package org.mike.gui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;

public class ContactButton extends JButton implements MouseListener {
    private final Color hoverBackgroundColor = new Color(0xDDDDDD);;
    private final Color pressedBackgroundColor = new Color(0xCCCCCC);

    public ContactButton(String text) {
        super(text);
        this.setupButton();
        this.addMouseListener(this);
//        this.setupMouseListener();
    }

    private void setupButton() {
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.setBorderPainted(false);
    }

//    private void setupMouseListener() {
//        this.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseEntered(MouseEvent e) {
//                setBackground(hoverBackgroundColor);
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//                setBackground(UIManager.getColor("control"));
//            }
//
//            @Override
//            public void mousePressed(MouseEvent e) {
//                setBackground(pressedBackgroundColor);
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//                setBackground(hoverBackgroundColor);
//            }
//        });
//    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed()) {
            g.setColor(pressedBackgroundColor);
        } else if (getModel().isRollover()) {
            g.setColor(hoverBackgroundColor);
        } else {
            g.setColor(getBackground());
        }
        g.fillRect(0, 0, getWidth(), getHeight());

        super.paintComponent(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
