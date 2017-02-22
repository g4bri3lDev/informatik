package cipher;

import java.util.*;

public class CipherNormal {
    private static final CipherNormal INSTANCE = new CipherNormal();
    private static final Scanner scanner = new Scanner(System.in);
    private final char[] key = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    public static void main(String[] args) {
        INSTANCE.mainLoop();
    }

    private void mainLoop() {
        System.out.println("Verschlüsseln(v), Entschlüsseln(e),Verschlüsseln  Caesar(vc),Entschlüsseln Caesar(ec), Keygen(g), beenden(x):");
        switch (scanner.next()) {
            case "v":
                System.out.println("Zu verschlüsselnden Text eingeben:");
                System.out.println("Verschlüsselter Text: " + INSTANCE.encode(scanner.next()) + "\n");
                mainLoop();
                break;
            case "e":
                System.out.println("Verschlüsselten Text eingeben:");
                System.out.println("Entschlüsselter Text: " + INSTANCE.decode(scanner.next()) + "\n");
                mainLoop();
                break;
            case "vc":
                System.out.println("Zu verschlüsselnden Text eingeben:");
                String text = scanner.next();
                System.out.println("Faktor eingeben:");
                int factor = scanner.nextInt();
                System.out.println("Verschlüsselter Text: " + INSTANCE.caesar(INSTANCE.encode(text), factor) + "\n");
                mainLoop();
                break;
            case "ec":
                System.out.println("Versclüsselten Text eingeben:");
                String text2 = scanner.next();
                System.out.println("negativen Faktor eingeben:");
                int nFactor = scanner.nextInt();
                System.out.println("Entschlüsselter Text: " + INSTANCE.decode(INSTANCE.caesar(text2, nFactor)) + "\n");
                mainLoop();
                break;
            case "g":

                System.out.println("Schlüssel: " + Arrays.toString(INSTANCE.randKey(key)));
                mainLoop();
                break;
            case "x":
                System.exit(0);
                break;
            default:
                mainLoop();
                break;
        }

    }

    private String encode(String text) {
        StringBuilder sb = new StringBuilder();
        int num;
        for (int i = 0; i < text.length(); i++) {
            num = Arrays.binarySearch(key, text.charAt(i));
            if (num < 10) {
                sb.append(0);
            }
            sb.append(num);
        }
        return sb.toString();
    }

    private String decode(String text) {
        StringBuilder sb = new StringBuilder();

        int num;
        for (int i = 0; i < text.length(); i++) {
            num = 10 * Character.getNumericValue(text.charAt(i)) + Character.getNumericValue(text.charAt(i + 1));
            sb.append(key[num]);
            i++;
        }

        return sb.toString();
    }

    private String caesar(String encText, int factor) {
        StringBuilder sb = new StringBuilder();
        int num;
        for (int i = 0; i < encText.length(); i++) {
            num = 10 * Character.getNumericValue(encText.charAt(i)) + Character.getNumericValue(encText.charAt(i + 1));
            num += factor;
            num = num > key.length ? num - key.length : num < 0 ? key.length + num : num;
            if (num < 10) sb.append(0);
            sb.append(num);
            i++;
        }
        return sb.toString();
    }


    private char[] randKey(char[] key) {
        List<Character> charList = new ArrayList<>();
        for (char c : key) {
            charList.add(c);
        }
        Collections.shuffle(charList);
        char[] retArr = new char[charList.size()];
        for (int i = 0; i < charList.size(); i++) {
            retArr[i] = charList.get(i);
        }
        return retArr;

    }
}
