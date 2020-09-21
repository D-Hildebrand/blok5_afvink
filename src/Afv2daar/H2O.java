package Afv2daar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class H2O extends JFrame implements ActionListener {

    private JTextField textfield; //declaratie, globaal
    private JTextField textfield1;
    private JLabel label;
    private JButton button;
    private JPanel panel; //Daar daan we weer, we declareren een raampje om op te tekenen -_- (1)

    public static void main(String[] args) {
        H2O frame = new H2O();
        frame.setSize(1000,800);
        frame.createGUI();
        frame.setVisible(true);

    }

    private void createGUI(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());
        window.setBackground(Color.white);

        setPanel();
        window.add(panel);

        label = new JLabel("X ->");
        window.add(label);
        textfield = new JTextField("X-Pix"); // insertie/instantiëring/initialisatie/
        textfield.setBackground(Color.white);
        window.add(textfield);

        JLabel label1 = new JLabel("Y ->");
        window.add(label1);
        textfield1 = new JTextField("Y-Pix"); // insertie/instantiëring/initialisatie/
        textfield1.setBackground(Color.white);
        window.add(textfield1);

        button = new JButton("Plaats molecuul");
        window.add(button);
        button.addActionListener(this);
    }

    public void setPanel(){
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(1000,700));
        panel.setBackground(Color.white);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String xaxis = textfield.getText();
        String yaxis = textfield1.getText();
        Graphics paper = panel.getGraphics();              //haal de referentie aan de panel op
        paper.clearRect(0, 0, 1000, 700);

        try {
            Integer.parseInt(xaxis);
            Integer.parseInt(yaxis);


        } catch (NumberFormatException e) {
            paper.drawString("Controlleer of de ingevoerde getallen integer zijn", 250, 250);
        }
        {
            int xAxis = Integer.parseInt(xaxis);
            int yAxis = Integer.parseInt(yaxis);

            // Ovale waterstof instellen met een blauwe kleur
            paper.setColor(Color.blue);
            paper.fillOval(xAxis, yAxis, 100, 100);
            paper.fillOval(xAxis, yAxis + 500, 100, 100);

            // Ovale zuurstof instellen, met een rode kleur
            paper.setColor(Color.red);
            paper.fillOval(xAxis + 220, yAxis + 220, 200, 200);

            // Verbindingen zichtbaar maken, met een zwarte lijn
            paper.setColor(Color.black);
            paper.drawLine(xAxis + 85, yAxis + 85, xAxis + 250, yAxis + 250);
            paper.drawLine(xAxis + 250, yAxis + 390, xAxis + 85, yAxis + 515);

        }
    }}

