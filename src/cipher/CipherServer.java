package cipher;

import javax.swing.*;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


@SuppressWarnings("ALL")
class CipherServer implements Runnable {
    private ServerSocket serverSocket;
    private Socket serverConnection;
    private ObjectOutputStream serverOut;
    private ObjectInputStream serverIn;

    public void run() {
        try {
            serverSocket = new ServerSocket(1337, 2);
            while (true) {
                try {
                    waitForConn();
                    setupIOStreams();
                    conversation();

                } catch (EOFException eofe) {
                    JOptionPane.showMessageDialog(null, "Server closed.", "Message", JOptionPane.INFORMATION_MESSAGE);
                } finally {
                    closeServer();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void waitForConn() throws IOException {
        System.out.println("SERVER - Waiting for incomming connection...");
        serverConnection = serverSocket.accept();
        System.out.println("SERVER - Connected to: " + serverConnection.getInetAddress().getHostName());

    }

    private void setupIOStreams() throws IOException {
        serverOut = new ObjectOutputStream(serverConnection.getOutputStream());
        serverOut.flush();
        serverIn = new ObjectInputStream(serverConnection.getInputStream());

    }

    private void conversation() throws IOException {
        String message = "Now Connected";
        //sendMessage(message);
        do {
            try {
                message = (String) serverIn.readObject();
                System.out.println(message);
                if (!message.equals("Now Connected"))
                    System.out.println("DECODED - " + CipherMain.decode(message.substring(CipherMain.username.length() + 3), 4));

            } catch (ClassNotFoundException cnfE) {
                cnfE.printStackTrace();
            }
        } while (!message.equals("CLIENT - END"));
    }

    private void closeServer() {
        System.out.println("SERVER  - Shutting down Server");
        try {
            serverOut.close();
            serverIn.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(String message) {
        try {
            serverOut.writeObject("SERVER - " + message);
            serverOut.flush();
            System.out.println(message);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
