package cipher;


import java.util.Arrays;
import java.util.Scanner;

public class CipherMain {

    private static final char[] key = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', ' '};
    private static final String keys = "abcdefghijklmnopqrstuvwxxyz";


    public static void main(String[] args) {
        System.out.println(Arrays.toString(encode(new Scanner(System.in).next())));
    }

    private static int[] encode(String text) {
        int[] out = new int[text.length()];
        int i = 0;
        for (char c : text.toCharArray()) {
            out[i] = c == ' ' ? 26 : keys.indexOf(c);
            //out[i] = keys.indexOf(c);
            System.out.println(out[i]);
            System.out.println(Arrays.binarySearch(key, c));

            i++;
        }

        return out;
    }
}
