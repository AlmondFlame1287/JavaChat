package chatjava.gui.chat;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import chatjava.gui.GPanel;

import javax.swing.JScrollPane;

public class GChat extends JPanel {
    private JScrollPane chatArea;

    private JTextArea chat = new JTextArea();
    private JButton sendButton = new JButton("Invia");

    public GChat() {
        this.setBounds(0, 520, 800, 80);
        this.setBackground(Color.GRAY);
        this.setupChatTextArea();
        this.setupButtons();
    }

    private void setupButtons() {
        sendButton.setBounds(720, 530, 70, 20);
        sendButton.addActionListener(evt -> {
            GPanel.getInstance().addMessage(chat.getText());
        });
        this.add(sendButton);
    }

    private void setupChatTextArea() {
        chat.setLineWrap(true);
        chat.setWrapStyleWord(true);
        chatArea = new JScrollPane(chat);

        chatArea.setBounds(10, 530, 700, 20);
        chatArea.setPreferredSize(new Dimension(700, 20));
        chatArea.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        this.add(chatArea);
    }

    public String getTextAreaText() {
        return chat.getText();
    }
}
