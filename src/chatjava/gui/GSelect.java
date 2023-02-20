package chatjava.gui;

import java.awt.Dimension;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import chatjava.tcp.ClientConnecter;

import static chatjava.gui.GFrame.changePanel;

public class GSelect extends JPanel {
    private final int connectionPort = 7777;

    private JLabel ipAddressLabel = new JLabel("Indirizzo IP: ");
    private JLabel nameLabel = new JLabel("Nome: ");
    private JLabel errorCode = new JLabel();

    private JTextField ipAddress = new JTextField();
    private JTextField nameField = new JTextField();

    private JButton confirmButton = new JButton("Conferma");
    private JButton host = new JButton("Fai da host");

    public GSelect() {
        this.setPreferredSize(new Dimension(800, 600));
        this.setupStuff();
    }

    private void sendConfirmation() {
        // TODO: Completa l'implementazione
        String ipAdd = ipAddress.getText();
        Pattern regex = Pattern.compile(
                "(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])");
        Matcher m = regex.matcher(ipAdd);

        if (m.find()) {
            ClientConnecter.getInstance().connect(ipAdd, connectionPort);

            changePanel("Chat", nameField.getText());
        } else {
            errorCode.setText("Impossibile connettersi. Indirizzo IP errato");
            this.revalidate();
        }
    }

    private void setupStuff() {
        // TODO: Rimuovere in pubblicazione
        ipAddress.setText("127.0.0.1");
        nameField.setText("Dib");

        ipAddress.setPreferredSize(new Dimension(160, 20));
        nameField.setPreferredSize(new Dimension(100, 20));

        host.addActionListener(evt -> changePanel("Server", null));
        this.add(host);

        this.add(ipAddressLabel);
        this.add(ipAddress);
        this.add(nameLabel);
        this.add(nameField);

        confirmButton.addActionListener(evt -> this.sendConfirmation());

        this.add(confirmButton);
        this.add(errorCode);
    }
}
