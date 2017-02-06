package cipher;


import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class CipherMain {

    private static final char[] key = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '-'};
    static String username = "G";
    private static CipherServer cipherServer = new CipherServer();
    private static CipherClient cipherClient = new CipherClient();
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

//        System.out.println(encode(new Scanner(System.in).next()));
        Thread server = new Thread(cipherServer);
        server.start();
        Thread client = new Thread(cipherClient);
        client.start();
        System.out.println("\n");
        while (true) {
            cipherClient.sendMessage(encode(in.next().toLowerCase()));
        }


    }

    public static String encode(String text) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                sb.append(",_,");
            } else {
                sb.append(Arrays.binarySearch(key, text.charAt(i)));
                if (!(i == text.length() - 1)) sb.append(",");

            }
        }
        return sb.toString();
    }

    public static String decode(String text) {
        String[] parts = (text.split(","));
        StringBuilder sb = new StringBuilder();
        for (String s : parts) {
            sb.append(key[Integer.parseInt(s)]);
        }
        return sb.toString();
    }

}
