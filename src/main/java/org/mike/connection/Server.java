package org.mike.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serv;
    private Socket client;
    private PrintWriter out;
    private BufferedReader in;


    private void checkConnection() throws IOException {
        String aloha = in.readLine();

        if(aloha.equals("Aloha")) {
            System.out.println("[SERVER CONNECTION] Aloha has been received");
            out.print("Aloha");
        } else {
            System.err.println("[SERVER CONNECTION] Aloha not received");
        }
    }

    public void startServer(int port) {
        try {
            this.serv = new ServerSocket(port);
            this.client = serv.accept();
            this.out = new PrintWriter(client.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            this.checkConnection();
        } catch (IOException ioe) {

        }
    }

    public void close() {
        try {
            this.client.close();
            this.serv.close();
            this.in.close();
            this.out.close();
        } catch (IOException ioe) {
            System.out.println("[SERVER] There was a problem: " + ioe.getMessage());
        }
    }
}
