package chatjava.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import chatjava.gui.GServ;

public class Server implements ConnectionInterface {
    private static Server instance = null;
    private ServerSocket serv;
    // private Socket client;

    private Server() {
    }

    public static Server getInstance() {
        if (instance == null) {
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
        if (!this.createServer(port)) {
            System.err.println("Qualcosa e' andato storto (Creazione server)");
            return;
        }

        new Thread() {
            @Override
            public void run() {
                try (Socket client = serv.accept();
                        BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()))) {
                    String inputLine;
                    while ((inputLine = br.readLine()) != null) {
                        GServ.getInstance().addReadMessage(inputLine);
                    }
                } catch (IOException ie) {
                    System.out.println(ie.getMessage());
                }
            }
        }.start();

    }

    @Override
    public void stopConnection() {
        if (this.serv == null)
            return;

        try {
            this.serv.close();
        } catch (IOException ie) {
            System.err.println("Qualcosa e' andato storto (Chiusura del server)");
        }
    }
}
