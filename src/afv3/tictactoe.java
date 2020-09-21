package afv3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class tictactoe extends JFrame implements ActionListener {

    private JButton button1, button2, button3, button4,
            button5, button6, button7, button8, button9;
    private JLabel label;
    private boolean turn;



    public static void main(String[] args) {
        //Setting up size for the GUI & calling other functions
        tictactoe frame = new tictactoe();
        frame.setSize(250, 300);
        frame.createGUI();
        frame.setVisible(true);
    }

    private void createGUI() {
        //Setting up default options
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());
        window.setBackground(Color.white);

        //Setting & adding buttons to listen to clicks
        button1 = new JButton("");
        button1.setPreferredSize(new Dimension(60,60));
        button1.addActionListener(this);
        button2 = new JButton("");
        button2.setPreferredSize(new Dimension(60,60));
        button2.addActionListener(this);
        button3 = new JButton("");
        button3.setPreferredSize(new Dimension(60,60));
        button3.addActionListener(this);
        button4 = new JButton("");
        button4.setPreferredSize(new Dimension(60,60));
        button4.addActionListener(this);
        button5 = new JButton("");
        button5.setPreferredSize(new Dimension(60,60));
        button5.addActionListener(this);
        button6 = new JButton("");
        button6.setPreferredSize(new Dimension(60,60));
        button6.addActionListener(this);
        button7 = new JButton("");
        button7.setPreferredSize(new Dimension(60,60));
        button7.addActionListener(this);
        button8 = new JButton("");
        button8.setPreferredSize(new Dimension(60,60));
        button8.addActionListener(this);
        button9 = new JButton("");
        button9.setPreferredSize(new Dimension(60,60));
        button9.addActionListener(this);
        window.add(button1);
        window.add(button2);
        window.add(button3);
        window.add(button4);
        window.add(button5);
        window.add(button6);
        window.add(button7);
        window.add(button8);
        window.add(button9);
    }

// Om inhoude van een variabele te toetsen, gebruik je de "equals" methode
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        turn = !turn;
        String cross;

        if (turn) {
            cross = "X";
        }   else {
            cross = "O";
        }

        Object source = actionEvent.getSource();
        if (source instanceof JButton) {JButton button = (JButton) source;
            button.setText(cross);
            }

    }
}
