package chatjava.gui;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import static chatjava.gui.GFrame.changePanel;
import static chatjava.gui.GProfile.setProfileName;

public class GSelect extends JPanel {
    private JLabel ipAddressLabel = new JLabel("Indirizzo IP: ");
    private JLabel nameLabel = new JLabel("Nome: ");

    private JTextField ipAddress = new JTextField();
    private JTextField nameField = new JTextField();

    private JButton confirmButton = new JButton("Conferma");

    public GSelect() {
        this.setPreferredSize(new Dimension(800, 600));
        this.setupStuff();
    }

    private void sendConfirmation() {
        setProfileName(nameField.getText());
        changePanel("Chat");
    }

    private void setupStuff() {
        ipAddress.setPreferredSize(new Dimension(160, 20));
        nameField.setPreferredSize(new Dimension(100, 20));

        this.add(ipAddressLabel);
        this.add(ipAddress);
        this.add(nameLabel);
        this.add(nameField);

        confirmButton.addActionListener(evt -> sendConfirmation());

        this.add(confirmButton);
    }
}
