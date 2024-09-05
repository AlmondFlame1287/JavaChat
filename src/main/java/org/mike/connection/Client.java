package org.mike.connection;

import org.mike.gui.components.MessageArea;

import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;

public class Client implements Runnable {
    // Logging utils
    private final static Logger clientLogger = Logger.getLogger(Client.class.getName());

    // Client stuff
    private final String ip;
    private final int port;

//    private PrintWriter serverWriter;
    private BufferedReader serverReader;

    private Socket socket;

    public Client(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void connect() {
        clientLogger.info("Starting connection");


        try {
            this.socket = new Socket(ip, port);
            clientLogger.info("Connection established");

            this.serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            this.serverWriter = new PrintWriter(socket.getOutputStream(), true);

            this.handleCommunication();
        } catch (IOException ioe) {
            clientLogger.severe("There was a problem connecting the client: " + ioe.getMessage());
            System.exit(-1);
        }
    }

    private void handleCommunication() {
        String serverMessage;

        try {
            while((serverMessage = serverReader.readLine()) != null) {
                // TODO: Check this
                clientLogger.info("Message got from server:" + serverMessage);
                MessageArea.getInstance().addMessage(serverMessage);
            }
        } catch (IOException ioe) {
            clientLogger.severe("Couldn't handle communication: " + ioe.getMessage());
            this.closeClient();
        }
    }

    public void closeClient() {
        try {
            this.socket.close();
            this.serverReader.close();
//            this.serverWriter.close();
        } catch (IOException ioe) {
            clientLogger.severe("Couldn't close client: " + ioe.getMessage());
            System.exit(-1);
        }
    }

    @Override
    public void run() {
        this.connect();
    }
}
