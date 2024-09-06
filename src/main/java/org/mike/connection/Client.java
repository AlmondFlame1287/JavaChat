package org.mike.connection;

import org.mike.Message;
import org.mike.User;
import org.mike.gui.components.ContactArea;
import org.mike.gui.components.MessageArea;
import static org.mike.common.Constants.PICTURE_PORT;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client implements Runnable {
    // Logging utils
    private final static Logger clientLogger = Logger.getLogger(Client.class.getName());

    // Client stuff
    private final String ip;
    private final int port;

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

            this.handleCommunication();
        } catch (IOException ioe) {
            clientLogger.severe("There was a problem connecting the client: " + ioe.getMessage());
            System.exit(-1);
        }
    }

    private void sendProfilePicture() {
        clientLogger.entering("Client", "sendProfilePicture");

        try (final Socket connection = new Socket(this.ip, PICTURE_PORT);
             final OutputStream outputStream = connection.getOutputStream();
             final FileInputStream pfpStream = new FileInputStream(User.getUser().getProfilePictureFile())
        ) {
            final byte[] buffer = new byte[4096];
            int bytesRead;

            while((bytesRead = pfpStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
                clientLogger.log(Level.INFO, "Wrote byte");
            }
        } catch (IOException ioe) {
            clientLogger.warning("There was a problem sending the pfp: " + ioe.getMessage());
        }

        clientLogger.exiting("Client", "sendProfilePicture");
    }

    private void handleCommunication() {
        String serverMessage;

        try {
            while((serverMessage = serverReader.readLine()) != null) {
                // TODO: Check this
                clientLogger.info("Message got from server:" + serverMessage);
                MessageArea.getInstance().addMessage(new Message(LocalDateTime.now(), serverMessage, ContactArea.getInstance().getContactName()));
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
        } catch (IOException ioe) {
            clientLogger.severe("Couldn't close client: " + ioe.getMessage());
            System.exit(-1);
        }
    }

    @Override
    public void run() {
        new Thread(this::sendProfilePicture).start();
        this.connect();
    }
}
