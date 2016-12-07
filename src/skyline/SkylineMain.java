package skyline;

import javax.swing.*;
import java.awt.*;


public class SkylineMain {

    public static void main(String[] args) {
        Skyline skyline = new Skyline("Skyline");
        skyline.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        skyline.setSize(400, 350);
        skyline.setVisible(true);
        skyline.setLocationRelativeTo(null);

    }


    private static class Skyline extends JFrame {

        private JLabel x1Label = new JLabel("X1");
        private JTextField x1In = new JTextField(15);
        private JLabel x2Label = new JLabel("X2");
        private JTextField x2In = new JTextField(15);
        private JLabel y1Label = new JLabel("Y1");
        private JTextField y1In = new JTextField(15);
        private JLabel y2Label = new JLabel("Y2");
        private JTextField y2In = new JTextField(15);
        private JButton button = new JButton("OK");
        private JLabel outL = new JLabel("Höhe: ");
        private JTextField outT = new JTextField(15);


        Skyline(String title) throws HeadlessException {

            super(title);
            setLayout(new FlowLayout());
            add(x1Label);
            add(x1In);
            add(x2Label);
            add(x2In);
            add(y1Label);
            add(y1In);
            add(y2Label);
            add(y2In);
            add(button);
            outT.setEditable(false);
            add(outL);
            add(outT);


            button.addActionListener(e -> {
                int x1 = Math.abs(Integer.parseInt(x1In.getText()));
                int x2 = Math.abs(Integer.parseInt(x2In.getText()));
                int y1 = Math.abs(Integer.parseInt(y1In.getText()));
                int y2 = Math.abs(Integer.parseInt(y2In.getText()));
                int xMin = (x1 < x2) ? x1 : x2;
                int yMin = (y1 < y2) ? y1 : y2;
                float distance = (float) Math.sqrt(Math.pow(xMin, 2) + Math.pow(yMin, 2));
                System.out.println(distance);
                int m;
                if (distance < 100) {
                    m = 0;
                } else {
                    String distanceString = String.valueOf(distance);
                    m = Integer.parseInt(distanceString.substring(distanceString.length() - 2, distanceString.length()));
                    System.out.println(m);
                }
                int height = 100 + m;

                outT.setText(String.valueOf(height));

            });


        }


    }
}
