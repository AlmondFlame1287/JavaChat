package org.mike.connection;

import org.mike.Message;
import org.mike.gui.components.ContactArea;

import static org.mike.common.Constants.COMMUNICATION_PORT;
import static org.mike.common.Constants.PICTURE_PORT;
import static org.mike.common.Constants.CONTACT_MESSAGES_PATH;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server implements Runnable {
    private static Server instance = null;

    // Logging utils
    private static final Logger serverLogger = Logger.getLogger(Server.class.getName());

    // Server stuff
    private ServerSocket communicationServer;
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

    public void startCommunicationServer() {
        try {
            serverLogger.info("Starting connection on port: " + COMMUNICATION_PORT);
            this.communicationServer = new ServerSocket(COMMUNICATION_PORT);
            this.connectedClient = this.communicationServer.accept();

            this.inputReader = new BufferedReader(new InputStreamReader(connectedClient.getInputStream()));
            this.outputStreamer = new PrintStream(connectedClient.getOutputStream());

            serverLogger.info("Connection established");
        } catch (IOException ioe) {
            serverLogger.severe("Couldn't start the server: " + ioe.getMessage());
            // TODO: Add offline mode detection
        }
    }

    public void startPictureServer() {
        serverLogger.entering("Server", "startPictureServer");

        try(
                final ServerSocket pictureServer = new ServerSocket(PICTURE_PORT);
                final Socket pictureClient = pictureServer.accept();
                final InputStream inputStream = pictureClient.getInputStream();
                final FileOutputStream toFileStream = new FileOutputStream(CONTACT_MESSAGES_PATH + "pfp.jpg");
                //  + ContactArea.getInstance().getContactName()
        ) {
            final byte[] buffer = new byte[4096]; // 4096 arbitrary value
            int bytesRead;

            while((bytesRead = inputStream.read(buffer)) != -1) {
                toFileStream.write(buffer, 0, bytesRead);
                serverLogger.log(Level.INFO, "Read a byte");
            }

        } catch (IOException ioe) {
            serverLogger.warning("Something went wrong with the picture server:" + ioe.getMessage());
        }

        serverLogger.exiting("Server", "startPictureServer");
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
            this.communicationServer.close();
        } catch(IOException ioe) {
            serverLogger.severe("There was a problem closing the server: " + ioe.getMessage());
            System.exit(-1);
        }
    }

    @Override
    public void run() {
        this.startCommunicationServer();
        new Thread(this::startPictureServer).start();
        this.handleCommunication();
    }
}
