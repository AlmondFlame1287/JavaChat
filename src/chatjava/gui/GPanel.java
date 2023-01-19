package chatjava.gui;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class GPanel extends JPanel {
    private JScrollPane chatArea;
    private JTextArea chat = new JTextArea();
    private JButton sendButton = new JButton("Invia");

    public GPanel() {
        this.setLayout(null);

        this.setupTextArea();
        this.setupButtons();
    }

    private void setupTextArea() {
        chat.setLineWrap(true);
        chat.setWrapStyleWord(true);
        chatArea = new JScrollPane(chat);

        chatArea.setBounds(10, 530, 700, 20);
        chatArea.setPreferredSize(new Dimension(700, 20));
        chatArea.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        this.add(chatArea);
    }

    private void setupButtons() {
        sendButton.setBounds(720, 530, 70, 20);
        this.add(sendButton);
    }
}