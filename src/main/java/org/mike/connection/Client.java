package org.mike.connection;

import org.mike.User;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket client;
    private PrintWriter out;
    private BufferedReader in;

    public void connect(String ip, int port) {
        try {
            System.out.println("[CLIENT][CONNECTION] Connected to: " + ip);
            this.client = new Socket(ip, port);
            this.in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            this.out = new PrintWriter(client.getOutputStream(), true);
            this.send("Aloha," + User.getUser().getName() + "," + client.getRemoteSocketAddress());
        } catch (IOException e) {
            System.err.println("[CLIENT][CONNECTION] There was a problem: " + e.getMessage());
            this.close();
        }
    }

    public void send(String msg) {
        try {
            out.println(msg);
        } catch (Exception e) {
            System.err.println("[CLIENT][MESSAGE_SENT] Couldn't send message: " + e.getMessage());
        }
    }

    public void close() {
        try {
            this.in.close();
            this.out.close();
            this.client.close();
        } catch (IOException e) {
            System.out.println("[CLIENT][CLOSE_CONNECITON] There was a problem: " + e.getMessage());
        }
    }
}
