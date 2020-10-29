/**
 * Dominic Hildebrand,
 * 29-10-2020
 */

package afv7;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


public class nucleotideVisualisator extends JFrame implements ActionListener {

    private JLabel label1;
    private JButton button1, button2;
    private JTextField textfield1;
    private JTextArea textarea1;
    private JPanel panel;

    /**
     * Main function
     * Sets basic parameters and calls other functions
     * @param args
     */
    public static void main(String[] args) {

        nucleotideVisualisator frame = new nucleotideVisualisator();
        frame.setSize(600, 500);
        frame.createGUI();
        frame.setVisible(true);

    }

    /**
     * Builds up the GUI
     */
    private void createGUI() {
        //Setting up default options
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());
        window.setBackground(Color.white);

        //Adding descriptive label and adding it
        label1 = new JLabel("App checkt voor peptide, dna of rna en weergeeft en" +
                "weergeeft per kleur wat er op die positie(s) staat");
        window.add(label1);

        //Textfield for showing errors / filename
        textfield1 = new JTextField("Bestandnaam komt hier te staan");
        textfield1.setPreferredSize(new Dimension(300, 24));
        window.add(textfield1);

        //Setting and deploying buttons
        button1 = new JButton("Open File");
        button1.addActionListener(this);
        window.add(button1);
        button2 = new JButton("Analyze...");
        button2.addActionListener(this);
        window.add(button2);

        //Setting and deploying text area
        textarea1 = new JTextArea();
        textarea1.setPreferredSize(new Dimension(550, 200));
        textarea1.setText("Hier komt de inputsequentie te staan.\n" +
                "Een limitatie van dit programma is dat deze tot 200 NT/AA kan weergeven." +
                "De laatste kleur neemt de rest van de balk in beslag als er minder dan " +
                "200 nt/aa aanwezig zijn.");
        window.add(textarea1);


        //Setting and deploying panel for drawing
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(600, 200));
        panel.setBackground(Color.lightGray);
        window.add(panel);


    }

    /**
     * Activates due to button, activates different action for each button
     * Sets file in text box for user to choose
     * Creates visual bar with different colours depending on aa/nt's
     * @param event
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        //If function for browsing for inputfile
        if (event.getSource() == button1) {
            JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

            int returnValue = fc.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fc.getSelectedFile();
                String filelocation = selectedFile.getAbsolutePath();

                textfield1.setText(filelocation);
            }
        }

        //Starts analyzing iputfile...
        if (event.getSource() == button2) {

            String fileloc = textfield1.getText();
            File inputfile = new File(fileloc);
            String sequence = "";

            //Checking if the inputfile works or not,
            //if it does work, creates string with the file's content
            try {
                Scanner filereader = new Scanner(new File(String.valueOf(inputfile)));
                while (filereader.hasNextLine()) {
                    sequence = sequence.concat(filereader.nextLine());
                }

            } catch (FileNotFoundException exception) {
                textarea1.setText("Bestand niet gevonden of werkt niet, selecteer een ander bestand.");
            }

            //Sets the inputseq to uppercase in case it's not
            sequence = sequence.toUpperCase();

            //Setting strings for comparing
            Set<String> polar = Set.of("N", "C", "Q", "S", "T", "Y");
            Set<String> apolar = Set.of("A", "I", "L", "M", "F", "P", "W", "V", "G");
            Set<String> neutral = Set.of("D", "E", "R", "K", "H");
            Set<String> dna = Set.of("A", "C", "G", "T");
            Set<String> rna = Set.of("A", "C", "G", "U");

            //Saves length of seq for later use
            int sequencelen = sequence.length();

            //List for colours later on, numbers get saved for colours
            List<Integer> colourlist = new ArrayList<>();

            //check for program to see which type this is
            boolean isPeptide = false;
            boolean isDNA = false;
            boolean isRNA = false;

            //For loop to see if it's DNA
            for (int i = 0; i < sequencelen; i++) {
                String character = String.valueOf(sequence.charAt(i));
                if (dna.contains(character)) {
                    isDNA = true;
                    if (character.equals("G")) {
                        colourlist.add(1);
                    } else if (character.equals("C")) {
                        colourlist.add(1);
                    } else if (character.equals("A")) {
                        colourlist.add(4);
                    } else {
                        colourlist.add(4);
                    }
                //If not DNA, set bool false, break loop
                } else {
                    isDNA = false;
                    break;
                }
            }

            //If not DNA, check if RNA
            if (!isDNA) {
                colourlist.clear();
                for (int i = 0; i < sequencelen; i++) {
                    String character = String.valueOf(sequence.charAt(i));
                    if (rna.contains(character)) {
                        isRNA = true;
                        if (character.equals("G")) {
                            colourlist.add(1);
                        }
                        if (character.equals("C")) {
                            colourlist.add(1);
                        }
                        if (character.equals("A")) {
                            colourlist.add(3);
                        } else {
                            colourlist.add(2);
                        }
                    //If not RNA, set bool false, break loop
                    } else {
                        isRNA = false;
                        break;
                    }
                }
            }
            //if not RNA, check if peptide
            if (!isRNA) {
                colourlist.clear();
                for (int i = 0; i < sequencelen; i++) {
                    String character = String.valueOf(sequence.charAt(i));
                        if (polar.contains(character) || apolar.contains(character) || neutral.contains(character)) {
                            isPeptide = true;
                            if (polar.contains(character)) {
                                colourlist.add(2);
                            }
                            if (apolar.contains(character)) {
                                colourlist.add(1);
                            } else {
                                colourlist.add(3);
                            }
                        //If not peptide, set 'error' character in text area, stop code
                        } else {
                            isPeptide = false;
                            textarea1.setText("Onbekend karakter gevonden: " + character);
                            return; //sort of custom exception
                        }

                }
            }
            //Set text in text area, set to which is found
            if (isDNA) {
                textarea1.setText("De ingevoerde DNAsequentie:\n" + sequence);
            } else if (isRNA) {
                textarea1.setText("De ingevoerde RNAsequentie:\n" + sequence);
            } else {
                textarea1.setText("De ingevoerde aminozuursequentie:\n" + sequence);
            }

            // in array colourlist:
            // 1 = RED
            // 2 = BLUE
            // 3 = GRAY
            // 4 = YELLOW

            // setting paper
            Graphics paper = panel.getGraphics();

            // Creating var for bar length
            int barlength = 0;

            // For loop for creating visual bar
            for (int i = 0; i < sequencelen; i++) {
                int step = colourlist.get(i);

                // Picking colour based on colourlist int
                if (step == 1) {paper.setColor(Color.RED);}
                else if (step == 2) {paper.setColor(Color.BLUE);}
                else if (step == 3) {paper.setColor(Color.GRAY);}
                else {paper.setColor(Color.YELLOW);}

                // Places the bar, sets text
                paper.fillRect(barlength, 100, (barlength + 3), 100);
                barlength = (barlength + 3);
            }


        }
    }
}