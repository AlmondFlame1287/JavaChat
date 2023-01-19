package chatjava.gui;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GChatPane extends JPanel {

    private static ArrayList<JLabel> texts = new ArrayList<>();
    private int prevTextY = 30;

    public GChatPane() {
        this.setBounds(0, 64, 800, 456);
        this.setBackground(Color.LIGHT_GRAY);
        // this.addMessagesToScreen();
    }

    public static void addMessage(String message) {
        // TODO: Prendere i messaggi dal database sql
        JLabel text = new JLabel(message);
        texts.add(text);
    }

    private void addMessagesToScreen() {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(400);
                    } catch (InterruptedException ie) {
                        System.out.println("Thread interrotto");
                        Thread.currentThread().interrupt();
                    }
                    int lastElement = texts.size() - 1;

                    System.out.println("precondizione 1");
                    if (lastElement < 0)
                        continue;

                    System.out.println("Array text size: " + texts.size());
                    JLabel lastMessageLabel = texts.get(lastElement);

                    String previousLastMessageString = lastMessageLabel.getText();
                    String newMessageString = texts.get(0).getText();

                    System.out.println("precondizione 2");

                    if (previousLastMessageString.equals(newMessageString))
                        continue;

                    System.out.println("Aggiunto");

                    prevTextY += 30;
                    lastMessageLabel.setBounds(30, prevTextY, 100, 20);
                    add(lastMessageLabel);
                }
            }
        }.start();
    }
}
