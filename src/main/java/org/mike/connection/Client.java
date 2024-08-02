package org.mike.connection;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket client;
    private PrintWriter out;
    private BufferedReader in;

    public void connect(String ip, int port) {
        try {
            this.client = new Socket(ip, port);
            this.in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            this.out = new PrintWriter(client.getOutputStream(), true);
        } catch (IOException e) {
            System.err.println("[CLIENT][CONNECTION] There was a problem: " + e.getMessage());
        }
    }

    public void send(String msg) {
        out.println(msg);
    }

    public void close() {
        try {
            this.in.close();
            this.out.close();
            this.client.close();
        } catch (IOException e) {
            System.out.println("[CLIENT][CLOSE_CONNECITON] There was a problem: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
