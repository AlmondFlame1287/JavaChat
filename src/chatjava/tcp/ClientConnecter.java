package chatjava.tcp;

import java.io.IOException;
import java.io.PrintWriter;
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
                } catch(IOException ie) {
                    System.out.println(ie.getMessage());
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
        try (PrintWriter out = new PrintWriter(this.connectionSocket.getOutputStream(), true)) {
            while(!this.connectionSocket.isClosed()) {
                out.println(text);
            }
        } catch(IOException ie) {
            System.out.println(ie.getMessage());
        }
        
        // try {
        //     BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connectionSocket.getOutputStream()));
        //     writer.write(text);
        //     writer.close();
        //     System.out.println("Messaggio inviato: " + text);
        // } catch (IOException ie) {
        //     System.out.println("Qualcosa e' andato storto (Scrittura messaggio)");
        //     ie.printStackTrace();
        // }
    }
}
