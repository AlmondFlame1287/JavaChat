package chatjava.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import chatjava.gui.GServ;

public class Server implements ConnectionInterface {
    private static Server instance = null;
    private ServerSocket serv;
    private Socket client;

    private Server() {
    }

    public static Server getInstance() {
        if(instance == null) {
            instance = new Server();
        }
        return instance;
    }

    private boolean createServer(final int port) {
        try {
            serv = new ServerSocket(port);
        } catch (IOException ie) {
            return false;
        }

        return true;
    }

    @Override
    public void connect(final String ipAddress, final int port) {
        if(!this.createServer(port)) {
            System.err.println("Qualcosa e' andato storto (Creazione server)");
            return;
        }

        new Thread() {
            @Override
            public void run() {
                while(!serv.isClosed()) {
                    try {
                        System.out.println("DEBUG: Server in attesa");
                        client = serv.accept();
                        System.out.println("DEBUG: Client connesso");

                        GServ.getInstance().addReadMessage(client.getInputStream());
    
                        // BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientOut));
    
                        // writer.write("Connessione effettuata sul server.");
                        
                        // writer.close();
                        // client.close();
                        // System.out.println("DEBUG: Connessione terminata");
                    } catch(IOException ie) {
                        System.err.println("Qualcosa e' andato storto (Server thread)");
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }.start();
    }

    @Override
    public void stopConnection() {
        if(this.serv == null) return;
        
        try {
            this.serv.close();
        } catch (IOException ie) {
            System.err.println("Qualcosa e' andato storto (Chiusura del server)");
        }
    }
}
