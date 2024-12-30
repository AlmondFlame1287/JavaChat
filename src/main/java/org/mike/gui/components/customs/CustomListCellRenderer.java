package org.mike.gui.components.customs;

import org.mike.Contact;

import javax.swing.*;
import java.awt.*;

public class CustomListCellRenderer extends JPanel implements ListCellRenderer<Contact> {
    private final JLabel label;

    public CustomListCellRenderer() {
        setLayout(new BorderLayout());
        label = new JLabel();
        label.setOpaque(false); // Transparency for modern look
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        add(label, BorderLayout.CENTER);
    }

    @Override
    public Component getListCellRendererComponent(
            JList<? extends Contact> list,
            Contact value,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {

        label.setText(value.getName());

        if (isSelected) {
            setBackground(new Color(102, 178, 255));
            label.setForeground(Color.WHITE);
        } else {
            setBackground(Color.WHITE);
            label.setForeground(Color.BLACK);
        }

        setBorder(BorderFactory.createLineBorder(isSelected ? new Color(51, 153, 255) : Color.LIGHT_GRAY, 2));
        return this;
    }
}
