package org.mike.connection;

import static org.mike.common.Constants.COMMUNICATION_PORT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
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
            serverLogger.info("Connection established");
        } catch (IOException ioe) {
            serverLogger.severe("Couldn't start the server: " + ioe.getMessage());
            // TODO: Add offline mode detection
        }
    }

    private void handleCommunication() {
        if(connectedClient == null) return;

        String message;

        try(BufferedReader inputReader = new BufferedReader(new InputStreamReader(connectedClient.getInputStream()))) {
            outputStreamer = new PrintStream(connectedClient.getOutputStream());

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

    public void send(String message) {
        outputStreamer.println(message);
    }

    public void closeServer() {
        try {
            this.connectedClient.close();
            this.serv.close();
            this.outputStreamer.close();
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
