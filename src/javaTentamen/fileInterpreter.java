package javaTentamen;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class fileInterpreter extends JFrame implements ActionListener {

    private JLabel label1;
    private JButton button1, button2, button3;
    private JTextField textfield1, textfield2;
    private JTextArea textarea1;
    private JScrollPane scrollpane;
    private JPanel panel;
    private JFrame errorframe;

    /**
     * Main function
     * Sets basic parameters and calls other functions
     */
    public static void main(String[] args) {

        fileInterpreter frame = new fileInterpreter();
        frame.setSize(600, 700);
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
        label1 = new JLabel("Deze app leest twee bestanden en vergelijkt de inhoud. " +
                "De eiwitten moeten beginnen met 'AT'");
        window.add(label1);

        //Textfield for showing filename 1
        textfield1 = new JTextField("Bestandsnaam bestand 1 komt hier te staan");
        textfield1.setPreferredSize(new Dimension(300, 24));
        window.add(textfield1);

        //Setting and deploying button for file1
        button1 = new JButton("Select File 1");
        button1.addActionListener(this);
        window.add(button1);

        //Textfield for showing filename 2
        textfield2 = new JTextField("Bestandsnaam bestand 2 komt hier te staan");
        textfield2.setPreferredSize(new Dimension(300, 24));
        window.add(textfield2);

        //Setting and deploying button for file 2
        button2 = new JButton("Select File 2");
        button2.addActionListener(this);
        window.add(button2);

        //Setting and deploying button for starting the analyse
        button3 = new JButton("Analyseer de twee bestanden");
        button3.addActionListener(this);
        button3.setPreferredSize(new Dimension(500, 25));
        window.add(button3);

        //Setting and deploying text area
        textarea1 = new JTextArea();
        textarea1.setEditable(false);
        textarea1.setLineWrap(true);
        textarea1.setWrapStyleWord(true);

        //Making the textarea scrollable
        scrollpane = new JScrollPane(textarea1);
        scrollpane.setPreferredSize(new Dimension(550, 200));
        scrollpane.createVerticalScrollBar();
        scrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);


        //Editing size and setting text for the textarea
        textarea1.setPreferredSize(new Dimension(550, 1000));
        textarea1.setText("Grootte eerste bestand: \n" +
                "Aantal unieke waardes eerste bestand: \n\n" +
                "Grootte tweede bestand: \n" +
                "Aantal unieke waardes tweede bestand\n\n" +
                "Aantal overlappende waardes: \n\n" +
                "Voer twee bestanden in: file 1 en file 2.\n" +
                "Deze bestanden worden met elkaar vergeleken.\n" +
                "Er wordt verwacht dat er een TSV geleverd wordt en dat de eiwitten beginnen met 'AT'");

        window.add(scrollpane);

        //Setting and deploying panel for drawing
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(600, 350));
        panel.setBackground(Color.lightGray);
        window.add(panel);


    }

    /**
     * Activates due to button, activates different action for each button
     * Sets files in text box for user to choose
     * Fills text area with number of overlapping values and unique values for protein codes.
     * Creates two ovals with a number of overlapping and unique values for protein codes.
     * Throws pop-up and stops code when protein code doesn't start with 'AT'
     *
     * @param event: the click of one of the three buttons
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        //If function for browsing for inputfile 1
        if (event.getSource() == button1) {
            JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

            int returnValue = fc.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fc.getSelectedFile();
                String filelocation = selectedFile.getAbsolutePath();

                textfield1.setText(filelocation);
            }
        }

        //If function for browsing for inputfile 2
        if (event.getSource() == button2) {
            JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

            int returnValue = fc.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fc.getSelectedFile();
                String filelocation = selectedFile.getAbsolutePath();

                textfield2.setText(filelocation);
            }
        }


        //Starts analyzing iputfiles...
        if (event.getSource() == button3) {

            //Starting to analyze file 1
            String fileloc = textfield1.getText();
            File inputfile = new File(fileloc);

            //Counter for file size
            int file1size = 0;

            //Creating a hashset for content comparing
            Set<String> file1content = new java.util.HashSet<>(Set.of());

            //Checking if the inputfile works or not,
            //If it does work, fills hashset for comparing
            try {
                Scanner filereader = new Scanner(new File(String.valueOf(inputfile)));

                while (filereader.hasNextLine()) {
                    String temp = (filereader.nextLine());
                    String[] firstpart = temp.split("\t");

                    //Checks if firstpart starts with AT and isn't Protein code, if both false, throws error
                    if (!firstpart[0].startsWith("AT") && !firstpart[0].equals("Protein code")) {
                        errorframe = new JFrame();
                        JOptionPane.showMessageDialog(errorframe, "Bestand 1 corrupt",
                                "Critical error", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    //To skip first line, if statement does nothing
                    if (firstpart[0].equals("Protein code")) {
                    } else {

                        file1size++;
                        //For unique list, if file 1 doesn't contain the protein code
                        if (!file1content.contains(firstpart[0])) {
                            file1content.add(firstpart[0]);
                        }
                    }
                }

            } catch (FileNotFoundException exception) {
                textarea1.setText("Bestand 1 niet gevonden of werkt niet.\n" +
                        "Selecteer een (ander) bestand of controlleer de inhoud.");
            }


            //Starting analysis of file 2

            fileloc = textfield2.getText();
            inputfile = new File(fileloc);

            //Counter for file size
            int file2size = 0;

            //Making hash set for content comparing
            Set<String> file2content = new java.util.HashSet<>(Set.of());

            //Checking if the inputfile works or not,
            //If it does work, fills hashset for comparing
            try {
                Scanner filereader = new Scanner(new File(String.valueOf(inputfile)));

                while (filereader.hasNextLine()) {
                    String temp = (filereader.nextLine());
                    String[] firstpart = temp.split("\t");

                    //Checks if firstpart starts with AT and isn't Protein code, if both false, throws error
                    if (!firstpart[0].startsWith("AT") && !firstpart[0].equals("Protein code")) {
                        errorframe = new JFrame();
                        JOptionPane.showMessageDialog(errorframe, "Bestand 2 corrupt",
                                "Critical error", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    //To skip first line, if statement does nothing
                    if (firstpart[0].startsWith("Protein code")) {
                    } else {
                        file2size++;
                        //For unique list, if file 2 doesn't contain the protein code
                        if (!file2content.contains(firstpart[0])) {
                            file2content.add(firstpart[0]);
                        }
                    }
                }

            } catch (FileNotFoundException exception) {
                textarea1.setText("Bestand 2 niet gevonden of werkt niet.\n" +
                        "Selecteer een (ander) bestand of controlleer de inhoud.");
            }

            //Removing protein code from the file
            file1content.remove("Protein code");
            file2content.remove("Protein code");
            file1size--;
            file2size--;

            //Counting how many objects are in each of the sets

            int file1unique = file1content.size();
            int overlap = 0;
            int file2unique = file2content.size();

            //Counting number of elements in file 1 that are also in file 2
            for (String s : file1content) {
                if (file2content.contains(s)) {
                    overlap++;
                }
            }

            //Setting found values in the textarea...
            textarea1.setText("Grootte eerste bestand: " + file1size + "\n" +
                    "Aantal unieke waardes eerste bestand: " + file1unique + "\n\n" +
                    "Grootte tweede bestand: " + file2size + "\n" +
                    "Aantal unieke waardes tweede bestand: " + file2unique + "\n\n" +
                    "Aantal overlappende waardes: " + overlap);

            //Getting graphics for drawing...
            Graphics paper = panel.getGraphics();

            //Drawing file 1 values
            paper.setColor(Color.BLUE);
            paper.drawOval(90, 125, 250, 100);
            paper.drawString(String.valueOf(file1unique), 195, 175);
            paper.drawString("Uniek uit bestand 1", 20, 100);

            //Drawing file 2 values
            paper.setColor(Color.RED);
            paper.drawOval(240, 125, 250, 100);
            paper.drawString(String.valueOf(file2unique), 385, 175);
            paper.drawString("Uniek uit bestand 2", 450, 100);

            //Drawing overlap
            paper.setColor(Color.BLACK);
            paper.drawString(String.valueOf(overlap), 290, 175);
            paper.drawString("Overlap in beide bestanden", 225, 290);


        }
    }
}