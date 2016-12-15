package klima.klima_adv;


import java.util.Scanner;

public class Klima {

    private Scanner scanner = new Scanner(System.in);
    private boolean onoff = false;
    private String tempDigit1 = " __ ", tempDigit2 = "|  |", tempDigit3 = "|__|", tempDigit4 = " __ ", tempDigit5 = "|  |", tempDigit6 = "|__|";
    private String stufe1 = "    ", stufe2 = "   |", stufe3 = "   |";


    public static void main(String[] args) {
        new Klima().display();

    }

    private void switchPower() {
        onoff = !onoff;
    }

    private void setTemp(int temp) {
        if (temp <= 15) {
            tempDigit1 = "    ";
            tempDigit2 = "   |";
            tempDigit3 = "   |";

            tempDigit4 = " __ ";
            tempDigit5 = "|__ ";
            tempDigit6 = " __|";

        } else if (temp >= 25) {
            tempDigit1 = " __ ";
            tempDigit2 = " __|";
            tempDigit3 = "|__ ";

            tempDigit4 = " __ ";
            tempDigit5 = "|__ ";
            tempDigit6 = " __|";
        }
        switch (temp) {
            case 16:
                tempDigit1 = "    ";
                tempDigit2 = "   |";
                tempDigit3 = "   |";

                tempDigit4 = " __ ";
                tempDigit5 = "|__ ";
                tempDigit6 = "|__|";
                break;
            case 17:
                tempDigit1 = "    ";
                tempDigit2 = "   |";
                tempDigit3 = "   |";

                tempDigit4 = " __ ";
                tempDigit5 = "   |";
                tempDigit6 = "   |";
                break;
            case 18:
                tempDigit1 = "    ";
                tempDigit2 = "   |";
                tempDigit3 = "   |";

                tempDigit4 = " __ ";
                tempDigit5 = "|__|";
                tempDigit6 = "|__|";
                break;
            case 19:
                tempDigit1 = "    ";
                tempDigit2 = "   |";
                tempDigit3 = "   |";

                tempDigit4 = " __ ";
                tempDigit5 = "|__|";
                tempDigit6 = " __|";
                break;
            case 20:
                tempDigit1 = " __ ";
                tempDigit2 = " __|";
                tempDigit3 = "|__ ";

                tempDigit4 = " __ ";
                tempDigit5 = "|  |";
                tempDigit6 = "|__|";
                break;
            case 21:
                tempDigit1 = " __ ";
                tempDigit2 = " __|";
                tempDigit3 = "|__ ";

                tempDigit4 = "    ";
                tempDigit5 = "   |";
                tempDigit6 = "   |";
                break;
            case 22:
                tempDigit1 = " __ ";
                tempDigit2 = " __|";
                tempDigit3 = "|__ ";

                tempDigit4 = " __ ";
                tempDigit5 = " __|";
                tempDigit6 = "|__ ";
                break;
            case 23:
                tempDigit1 = " __ ";
                tempDigit2 = " __|";
                tempDigit3 = "|__ ";

                tempDigit4 = " __ ";
                tempDigit5 = " __|";
                tempDigit6 = " __|";
                break;
            case 24:
                tempDigit1 = " __ ";
                tempDigit2 = " __|";
                tempDigit3 = "|__ ";

                tempDigit4 = "    ";
                tempDigit5 = "|__|";
                tempDigit6 = "   |";
                break;
        }

    }

    private void setStufe(int stufe) {
        if (stufe <= 1) {
            stufe1 = "    ";
            stufe2 = "   |";
            stufe3 = "   |";
        } else if (stufe >= 4) {
            stufe1 = "    ";
            stufe2 = "|__|";
            stufe3 = "   |";
        }
        switch (stufe) {
            case 2:
                stufe1 = " __ ";
                stufe2 = " __|";
                stufe3 = "|__ ";
                break;
            case 3:
                stufe1 = " __ ";
                stufe2 = " __|";
                stufe3 = " __|";
                break;

        }
    }

    private void display() {
        System.out.print('\u000c');//Escape shit

        String onoffString;
        onoffString = onoff ? "EIN" : "AUS";
        if (!onoff) {
            System.out.println("+------------------------------------------+--------------------------------------------+");
            System.out.println("|               Klima:                     |                       " + onoffString + "                  |");
            System.out.println("+------------------------------------------+--------------------------------------------+");
            System.out.println("|  Aktion: (p = einschalten, e = beenden)                                               |");
            System.out.println("+------------------------------------------+--------------------------------------------+");
            switch (scanner.next()) {
                case "p":
                    switchPower();
                    display();
                    break;
                case "e":
                    System.exit(0);
                    break;
                default:
                    display();
                    break;
            }

        } else {

            System.out.println("+------------------------------------------+--------------------------------------------+");
            System.out.println("|               Klima:                     |                       " + onoffString + "                  |");
            System.out.println("+------------------------------------------+--------------------------------------------+");
            System.out.println("|                                          |                                            |");
            System.out.println("|             " + tempDigit1 + "  " + tempDigit4 + "                   |                       " + stufe1 + "                 |");
            System.out.println("|             " + tempDigit2 + "  " + tempDigit5 + "                   |                       " + stufe2 + "                 |");
            System.out.println("|             " + tempDigit3 + "  " + tempDigit6 + "                   |                       " + stufe3 + "                 |");
            System.out.println("|                                          |                                            |");
            System.out.println("+------------------------------------------+--------------------------------------------+");
            System.out.println("|  Aktion: (p = ausschalten, t = Temperatur, s = Stufe                                  |");
            System.out.println("+------------------------------------------+--------------------------------------------+");
            switch (scanner.next()) {
                case "p":
                    switchPower();
                    display();
                    break;
                case "t":
                    System.out.println("neue Temperatur: ");
                    setTemp(scanner.nextInt());
                    display();
                    break;
                case "s":
                    System.out.println("neue Stufe: ");
                    setStufe(scanner.nextInt());
                    display();
                    break;
                default:
                    display();
                    break;
            }
        }

    }

}
