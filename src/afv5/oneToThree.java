package afv5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class oneToThree extends JFrame implements ActionListener {

    //Placing all the things that are going to be used
    private JTextField textfield1, textfield2;
    private JLabel label;
    private JButton button;


    public static void main(String[] args) {

        oneToThree frame = new oneToThree();
        frame.setSize(500, 200);
        frame.createGUI();
        frame.setVisible(true);

    }

    private void createGUI() {
        //Setting up default options
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());
        window.setBackground(Color.white);

        //Adding descriptive label and adding it
        label = new JLabel("Voer een enkelletterige aminozuursequentie in," +
                " ik vertaal het naar drieletterige code.");
        window.add(label);

        //Adding input textfield, setting the size
        textfield1 = new JTextField("Input");
        textfield1.setPreferredSize(new Dimension(450, 24));
        window.add(textfield1);

        //Adding button to allow for translation
        button = new JButton("Vertaal deze sequentie!");
        window.add(button);
        button.addActionListener(this);

        //Adding output textfield
        textfield2 = new JTextField("Hier komt de output te staan");
        textfield2.setPreferredSize(new Dimension(450, 48));
        window.add(textfield2);


    }

    @Override
    public void actionPerformed(ActionEvent event) {
        //Declaring variables
        String input = textfield1.getText();
        input = input.toUpperCase();
        String output = "";

        //Must create for loop, translator won't accept more than one char in string
        for (int i = 0; i < input.length(); i++) {
            char temp = input.charAt(i);
            String toTranslate = String.valueOf(temp);

            try {
                output = output.concat("-" + afv5.Translator.one2three(toTranslate));

                //To not have a connecting stripe to start
                if (i == 0) {
                    output = output.substring(1);
                }
            } catch (Exception e) {
                output = e.getMessage();
                break;
            }
        }

        //Output in textfield for easy copy-paste
        textfield2.setText(output);

    }
}