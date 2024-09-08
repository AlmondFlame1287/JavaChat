package org.mike.connection;

import org.mike.Message;
import org.mike.gui.components.ContactArea;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.mike.common.Constants.*;

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

    public boolean startPictureServer() {
        serverLogger.entering("Server", "startPictureServer");

        // Get contact name is always null because contacts haven't been initialized yet
        // Hence removed
        final String filePath = CONTACT_MESSAGES_PATH.toString() + File.separatorChar + "pfp.jpg";

        try(
                final ServerSocket pictureServer = new ServerSocket(PICTURE_PORT);
                final Socket pictureClient = pictureServer.accept();
                final InputStream inputStream = pictureClient.getInputStream();
                final FileOutputStream toFileStream = new FileOutputStream(filePath)
        ) {
            final byte[] buffer = new byte[4096]; // 4096 arbitrary value
            int bytesRead;

            while((bytesRead = inputStream.read(buffer)) != -1) {
                toFileStream.write(buffer, 0, bytesRead);
                serverLogger.log(Level.INFO, "Read a byte");
            }

            return true;
        } catch (IOException ioe) {
            serverLogger.warning("Something went wrong with the picture server:" + ioe.getMessage());
            return false;
        } finally {
            serverLogger.exiting("Server", "startPictureServer");
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
            this.communicationServer.close();
        } catch(IOException ioe) {
            serverLogger.severe("There was a problem closing the server: " + ioe.getMessage());
            System.exit(-1);
        }
    }

    private void renamePfp() {
        final Path pfpPath = Paths.get(CONTACT_MESSAGES_PATH + "/pfp.jpg").normalize();
        final Path newPfpPath = Paths.get(CONTACT_MESSAGES_PATH + "/" + ContactArea.getInstance().getContactName() + ".jpg").normalize();

        try {
            Files.copy(pfpPath, newPfpPath, REPLACE_EXISTING);
        } catch (IOException ignored) {}
    }

    @Override
    public void run() {
        final boolean imageReceived = this.startPictureServer();

        if(imageReceived) {
            final ContactArea contactArea = ContactArea.getInstance();
            contactArea.getContact().loadProfilePicture();
            contactArea.drawProfilePicture();
            this.renamePfp();
        }

        this.startCommunicationServer();
        this.handleCommunication();
    }
}
