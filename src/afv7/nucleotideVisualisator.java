package afv7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class nucleotideVisualisator extends JFrame implements ActionListener {
    public static void main(String[] args) {
        nucleotideVisualisator frame = new nucleotideVisualisator();
        frame.setSize(600, 500);
        frame.createGUI();
        frame.setVisible(true);

    }

    private void createGUI() {
        //Setting up default options
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());
        window.setBackground(Color.white);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
