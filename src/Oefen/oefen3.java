package Oefen;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class oefen3 extends JFrame implements ActionListener {

    private JTextField textfield; //declaratie, globaal
    private JLabel label;
    private JButton button;
    private JPanel panel; //Daar daan we weer, we declareren een raampje om op te tekenen -_- (1)

    public static void main(String[] args) {
        oefen3 frame = new oefen3();
        frame.setSize(500,500);
        frame.createGUI();
        frame.setVisible(true);

    }

    private void createGUI(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());
        label = new JLabel("vieze vuile venster");
        window.add(label);

        button = new JButton("niet om mij klikken");
        window.add(button);
        textfield = new JTextField("geef mij input"); // insertie/instantiëring/initialisatie/
        textfield.setBackground(Color.BLACK);
        window.add(textfield);
        window.setBackground(Color.darkGray);
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(200,200));
        panel.setBackground(Color.red);
        window.add(panel);
        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        textfield.setText("ER IS GEKLIKT OWO");
        label.setText("OwO OwO OwO");

        Graphics paper = panel.getGraphics(); //haal de referntie aan de panel op
        paper.drawLine(10,20,50,50);
        paper.setColor(Color.green);
        paper.drawString(textfield.getText(), 70, 80);
    }
}

