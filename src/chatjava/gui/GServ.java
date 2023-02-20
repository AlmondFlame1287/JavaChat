package chatjava.gui;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import chatjava.tcp.Server;

import static chatjava.gui.GFrame.changePanel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GServ extends JPanel {
    private static GServ instance = null;

    private JLabel title = new JLabel("SERVER MODE");
    private BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);

    private JButton startButton = new JButton("Start");
    private JButton stopButton = new JButton("Stop");
    private JButton back = new JButton("Indietro");

    private GServ() {
        this.setLayout(layout);
        this.add(title);

        this.setup();
    }

    public static GServ getInstance() {
        if(instance == null) {
            instance = new GServ();
        }
        return instance;
    }

    private void setup() {
        startButton.addActionListener(evt -> this.start());
        this.add(startButton);

        stopButton.addActionListener(evt -> this.stop());
        this.add(stopButton);

        back.addActionListener(evt -> {
            this.stop();
            changePanel("Select", null);
        });
        this.add(back);
    }

    private void start() {
        Server.getInstance().connect(null, 7777);
        startButton.setEnabled(false);
        stopButton.setEnabled(true);
    }

    private void stop() {
        Server.getInstance().stopConnection();
        startButton.setEnabled(true);
        stopButton.setEnabled(false);
    }

    public void addReadMessage(InputStream messageStream) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(messageStream))){
            System.out.println("Messaggio ricevuto: " + br.readLine());
            this.add(new JLabel(br.readLine()));
            this.revalidate();
            this.repaint();
        } catch (IOException ie) {
            System.err.println("Qualcosa e' andato storto (Aggiunta messaggio)");
            ie.printStackTrace();
        }
    }
}
