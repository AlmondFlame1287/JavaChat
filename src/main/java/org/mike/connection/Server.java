package org.mike.connection;

import org.mike.gui.components.MessageArea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    private ServerSocket serv;
    private Socket client;
    private PrintWriter out;
    private BufferedReader in;

    public Server() {}

    private void checkConnection() throws IOException {
        String aloha = in.readLine();

        if(aloha.equals("Aloha")) {
            System.out.println("[SERVER CONNECTION] Aloha has been received");
            out.print("Aloha");
        } else {
            System.err.println("[SERVER CONNECTION] Aloha not received");
        }
    }

    private void startServer() {
        try {
            System.out.println("[SERVER][START] Server started on port " + 3333);
            this.serv = new ServerSocket(3333);
            this.client = serv.accept();
            this.out = new PrintWriter(client.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            this.checkConnection();
            this.handleText();
        } catch (IOException ioe) {
            System.err.println("[SERVER][START] There was a problem starting the server:" + ioe.getMessage());
        }
    }

    private void handleText() {
        while(this.client.isConnected()) {
            try {
                String read = in.readLine();
                MessageArea.getInstance().addMessage(read);
                System.out.println("[SERVER][HANDLE_TEXT][INFO] Text received: " + read);
            } catch (IOException e) {
                System.err.println("[SERVER][HANDLE_TEXT] There was a problem handling text: " + e.getMessage());
            }
        }
    }

    public void close() {
        try {
            this.client.close();
            this.serv.close();
            this.in.close();
            this.out.close();
            System.out.println("[SERVER][STOP] Server has successfully stopped");
        } catch (IOException ioe) {
            System.out.println("[SERVER][STOP] There was a problem: " + ioe.getMessage());
        }
    }

    @Override
    public void run() {
        this.startServer();
    }
}
