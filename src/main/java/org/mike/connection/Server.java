package org.mike.connection;

import org.mike.Message;

import static org.mike.common.Constants.COMMUNICATION_PORT;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class Server implements Runnable {
    private static Server instance = null;

    // Logging utils
    private static final Logger serverLogger = Logger.getLogger(Server.class.getName());

    // Server stuff
    private ServerSocket serv;
    private Socket connectedClient;

    // Input and output stuff
    private BufferedReader inputReader;
    private PrintStream outputStreamer;

    private Server() {}

    public static Server getInstance() {
        if(instance == null)
            instance = new Server();
        return instance;
    }

    public void startServer() {
        try {
            serverLogger.info("Starting connection on port: " + COMMUNICATION_PORT);
            serv = new ServerSocket(COMMUNICATION_PORT);
            connectedClient = serv.accept();

            this.inputReader = new BufferedReader(new InputStreamReader(connectedClient.getInputStream()));
            this.outputStreamer = new PrintStream(connectedClient.getOutputStream());

            serverLogger.info("Connection established");
        } catch (IOException ioe) {
            serverLogger.severe("Couldn't start the server: " + ioe.getMessage());
            // TODO: Add offline mode detection
        }
    }

    private void handleCommunication() {
        serverLogger.entering("Server", "handleCommunication");
        if(connectedClient == null) {
            serverLogger.warning("Connected client is null");
            return;
        }

        String message;

        try {
            while(!connectedClient.isClosed()) {
                if((message = inputReader.readLine()) == null) {
                    serverLogger.severe("NULL MESSAGE DETECTED, SHUTTING DOWN CONNECTION");
                    this.closeServer();
                    break;
                }

                serverLogger.info("Message got from client: " + message);
            }

        } catch(IOException ioe) {
            serverLogger.severe("There was a problem handling communication: " + ioe.getMessage());
        }
    }

    public void send(Message message) {
        assert this.outputStreamer != null;
        this.outputStreamer.println(message.getText());
    }

    public void closeServer() {
        try {
            this.connectedClient.close();
            this.serv.close();
        } catch(IOException ioe) {
            serverLogger.severe("There was a problem closing the server: " + ioe.getMessage());
            System.exit(-1);
        }
    }

    @Override
    public void run() {
        this.startServer();
        this.handleCommunication();
    }
}
