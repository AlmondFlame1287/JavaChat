package chatjava.tcp;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientConnecter implements ConnectionInterface {
    private static ClientConnecter instance = null;
    private Socket connectionSocket;

    private PrintWriter out;

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
        try {
            connectionSocket = new Socket(ipAddress, port);
        } catch (IOException ie) {
            System.out.println(ie.getMessage());
        }
    }

    @Override
    public void stopConnection() {
        if (this.connectionSocket == null)
            return;

        try {
            this.connectionSocket.close();
            if (this.out != null)
                this.out.close();
        } catch (IOException ie) {
            System.err.println("Qualcosa e' andato storto (Chiusura connessione)");
        }
    }

    public void sendMessage(String text) {
        try {
            if (this.out == null)
                out = new PrintWriter(this.connectionSocket.getOutputStream(), true);
            out.println(text);
        } catch (IOException ie) {
            System.out.println(ie.getMessage());
        }
    }
}
