package klima.klima_adv;

/**
 * Created by gabriel on 12/2/16.
 */
public class KlimaAdv {

    public static void main(String[] args) {
        for (int i = 0; i < 9; i++) {
            printDigit(i);
        }

    }

    static void printDigit(int d) {
        if (d >= 0 && d < 10) {
            switch (d) {
                case 0:
                    System.out.println(" __ \n|  |\n|__|");
                    break;
                case 1:
                    System.out.println("    \n   |\n   |");
                    break;
                case 3:
                    System.out.println(" __ \n __|\n __|");
                    break;
                case 4:
                    System.out.println("    \n|__|\n   |");
                    break;
                case 5:
                    System.out.println(" __ \n|__ \n __|");
                    break;
                case 6:
                    System.out.println(" __ \n|__ \n|__|");
                    break;
                case 7:
                    System.out.println(" __ \n   |\n   |");
                    break;
                case 8:
                    System.out.println(" __ \n|__|\n|__|");
                    break;
                case 9:
                    System.out.println(" __ \n|__|\n __|");
            }

        }
    }
}
