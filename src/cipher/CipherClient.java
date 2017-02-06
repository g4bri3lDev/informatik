package cipher;


import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

@SuppressWarnings("Duplicates")
class CipherClient implements Runnable {
    private ObjectInputStream clientIn;
    private ObjectOutputStream clientOut;
    private String serverIP = "192.168.0.25";
    private Socket clientConnection;
    private CipherMain cm = new CipherMain();

    public void run() {
        if (serverIP.equals("")) serverIP = new Scanner(System.in).next();
        try {
            connectToServer();
            setupIOStreams();
            conversation();

        } catch (EOFException eofe) {
            System.out.println("CLIENT - Client closed connection");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            closeClient();
        }
    }

    private void connectToServer() throws IOException {
        System.out.println("CLIENT - Trying to Connect");
        clientConnection = new Socket(InetAddress.getByName(serverIP), 1337);
        System.out.println("CLIENT - Connected to " + clientConnection.getInetAddress().getHostName());
    }

    private void setupIOStreams() throws IOException {
        clientOut = new ObjectOutputStream(clientConnection.getOutputStream());
        clientOut.flush();
        clientIn = new ObjectInputStream(clientConnection.getInputStream());
    }

    private void conversation() throws IOException {
        String message = "";

        do {

            try {
                message = (String) clientIn.readObject();
                System.out.println(message);


            } catch (ClassNotFoundException cnfe) {
                cnfe.printStackTrace();
            }
        }
        while (!message.equals("SERVER - END"));

    }

    private void closeClient() {
        System.out.println("CLIENT - Closing Client");
        try {
            clientOut.close();
            clientIn.close();
            clientConnection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        try {
            clientOut.writeObject("CLIENT - " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
