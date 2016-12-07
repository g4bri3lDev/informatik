package klima;


import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;

public class Klima {
    public static void main(String[] args) {
        new KlimaFrame().setVisible(true);

    }


    private static class KlimaFrame extends JFrame {
        Auto auto = new Auto();
        JPanel panel = new JPanel();
        JToggleButton onoff = new JToggleButton();
        JSlider[] sliders = new JSlider[3];



        KlimaFrame() {
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            ChangeListener1 changeListener = new ChangeListener1();
            panel.setLayout(new GridLayout(4, 1));
            panel.add(onoff);
            for (JSlider js: sliders){
                js = new JSlider();
                js.setPaintLabels(true);
                js.setPaintTicks(true);
                js.setMinorTickSpacing(1);
                js.addChangeListener(changeListener);

            }
            sliders[0].setMinimum(10);
            sliders[0].setMaximum(35);
            sliders[0].setValue((int) auto.getTemp());


            for (JSlider jSlider: sliders){
                panel.add(jSlider);
            }

            this.getContentPane().add(panel);
            pack();


        }


        private class ChangeListener1 implements javax.swing.event.ChangeListener {

            @Override
            public void stateChanged(ChangeEvent e) {

            }
        }
    }

    private static class Auto {
        boolean power = false;
        float temp = 21.0f, fan;
        char fanLoc;


        void switchPower() {
            power = !power;
        }

        void setTemp(float temp) {
            this.temp = temp;
        }

        void setFan(float fan) {
            this.fan = fan;
        }

        void setFanLoc(char location) {
            fanLoc = location;

        }

        public float getTemp() {
            return temp;
        }

        public float getFan() {
            return fan;
        }

        public char getFanLoc() {
            return fanLoc;
        }
    }


}
