package quersumme;


import javax.swing.*;

public class Quersumme {
    private static int result = 0;

    public static void main(String[] args) {

        String s = JOptionPane.showInputDialog(null, "Zahl eingeben", "", JOptionPane.QUESTION_MESSAGE);
        char[] chars = s.toCharArray();
        System.out.println(chars.length);

        for (char c : chars) {
            result += Character.getNumericValue(c);
        }
        JOptionPane.showMessageDialog(null, String.valueOf(result), "Ergebnis", JOptionPane.INFORMATION_MESSAGE);

    }
}
