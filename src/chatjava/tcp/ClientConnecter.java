package chatjava.tcp;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientConnecter implements ConnectionInterface {
    private static ClientConnecter instance = null;
    private Socket connectionSocket;

    private ClientConnecter() {

    }

    public static ClientConnecter getInstance() {
        if (instance == null) {
            instance = new ClientConnecter();
        }
        return instance;
    }

    @Override
    public void connect(final String ipAddress, final int port) {
        new Thread(){
            @Override
            public void run() {
                try {
                    connectionSocket = new Socket(ipAddress, port);
                    System.out.println("DEBUG: Connessione iniziata");
                } catch (IOException ie) {
                    System.err.println("Qualcosa e' andato storto (Connessione al server)");
                }
            }
        }.start();
    }

    @Override
    public void stopConnection() {
        if (this.connectionSocket == null)
            return;

        try {
            this.connectionSocket.close();
        } catch (IOException ie) {
            System.err.println("Qualcosa e' andato storto (Chiusura connessione)");
        }
    }

    public void sendMessage(String text) {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connectionSocket.getOutputStream()));
            writer.write(text);
            System.out.println("Messaggio inviato: " + text);
        } catch (IOException ie) {
            System.out.println("Qualcosa e' andato storto (Scrittura messaggio)");
            ie.printStackTrace();
        }
    }
}
