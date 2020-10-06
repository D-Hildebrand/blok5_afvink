package afv4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;

public class snailrace extends JFrame implements ActionListener {

    private JPanel panel;
    private JLabel label;
    private JButton button;

    public static void main(String[] args) {
        //Setting up size for the GUI & calling other functions
        snailrace frame = new snailrace();
        frame.setSize(1000, 500);
        frame.createGUI();
        frame.setVisible(true);
    }

    private void createGUI() {
        //Setting up default options
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());
        window.setBackground(Color.white);

        //Setting up GUI environment
        setPanel();
        window.add(panel);

        label = new JLabel("Racetijd! Wie gaat er winnen?");
        window.add(label);

        button = new JButton("Klik om te starten");
        window.add(button);
        button.addActionListener(this);
    }

    public void setPanel() {
        //Setting up a simple panel to deploy
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(1000, 400));
        panel.setBackground(Color.white);
    }

    public class slak {
        String naam;
        int distance;

        public slak(String naam, int distance) {
        }

        public void setDistance(int dist) {
            if (dist > distance) {
                distance = dist;
            }
        }

        public int getDistance() {
            return distance;
        }

        public String getNaam() {
            return naam;
        }

        public void setNaam(String naam) {
            this.naam = naam;
        }
    }

    public void pauzeer(int msec) {
        try {
            Thread.sleep(msec);
        } catch (InterruptedException e) {
            System.out.println("Pauze interruptie");
        }
    }


    public void actionPerformed(ActionEvent event) {
        //Setting x-loc. for the contestants
        slak s1 = new slak("gijsbert", 0);
        slak s2 = new slak("ronnie", 0);
        slak s3 = new slak("eva", 0);
        slak s4 = new slak("karel", 0);

        System.out.println(s1);

        //Drawing the race
        Graphics paper = panel.getGraphics();
        paper.clearRect(0, 0, 1000, 400);

        //Magenta finish line ;P
        paper.setColor(Color.magenta);
        paper.fillRect(800, 0, 25, 400);

        //racing...
        while (s1.getDistance() < 800 ||
                s2.getDistance() < 800 ||
                s3.getDistance() < 800 ||
                s4.getDistance() < 800) {

            //Afstand met willekeurige interval verhogen
            pauzeer(8);
            s1.setDistance(ThreadLocalRandom.current().nextInt(s1.getDistance(), (s1.getDistance() + 10)));
            s2.setDistance(ThreadLocalRandom.current().nextInt(s2.getDistance(), (s2.getDistance() + 10)));
            s3.setDistance(ThreadLocalRandom.current().nextInt(s3.getDistance(), (s3.getDistance() + 10)));
            s4.setDistance(ThreadLocalRandom.current().nextInt(s3.getDistance(), (s4.getDistance() + 10)));

            //Maakt het pad van de race zichtbaar
            paper.setColor(Color.green);
            paper.fillRect(0, 0, s1.getDistance(), 100);
            paper.setColor(Color.red);
            paper.fillRect(0, 100, s2.getDistance(), 100);
            paper.setColor(Color.blue);
            paper.fillRect(0, 200, s3.getDistance(), 100);
            paper.setColor(Color.black);
            paper.fillRect(0, 300, s4.getDistance(), 100);


        }

    }

}
