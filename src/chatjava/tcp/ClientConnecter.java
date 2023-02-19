package chatjava.tcp;

import java.io.IOException;
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
    public void connect(String ipAddress, int port) {
        try {
            this.connectionSocket = new Socket(ipAddress, port);
            System.out.println("Connessione iniziata");
        } catch (IOException ie) {
            System.err.println("Qualcosa e' andato storto (Connessione al server)");
        }
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
}
