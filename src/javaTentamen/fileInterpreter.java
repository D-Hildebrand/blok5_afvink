package javaTentamen;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class fileInterpreter extends JFrame implements ActionListener {

    private JLabel label1;
    private JButton button1, button2;
    private JTextField textfield1;
    private JTextArea textarea1;
    private JScrollPane scrollpane;
    private JPanel panel;

    /**
     * Main function
     * Sets basic parameters and calls other functions
     *
     * @param args
     */
    public static void main(String[] args) {

        fileInterpreter frame = new fileInterpreter();
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
        button1 = new JButton("Choose File");
        button1.addActionListener(this);
        window.add(button1);
        button2 = new JButton("Analyze...");
        button2.addActionListener(this);
        window.add(button2);

        //Setting and deploying text area
        textarea1 = new JTextArea();
//        textarea1.setEditable(false);
        textarea1.setLineWrap(true);
        textarea1.setWrapStyleWord(true);

        //Making the textarea scrollable
        scrollpane = new JScrollPane(textarea1);
        scrollpane.setPreferredSize(new Dimension(550, 200));
        scrollpane.createVerticalScrollBar();
        scrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);



        //Editing size and setting text for the textarea
        textarea1.setPreferredSize(new Dimension(550, 1000));
        textarea1.setText("Hier komt de inputsequentie te staan.\n\n" +
                "Een limitatie van dit programma is dat deze tot 200 NT/AA kan weergeven.\n" +
                "De laatste kleur neemt de rest van de balk in beslag als er minder dan " +
                "200 nt/aa aanwezig zijn.");

        window.add(scrollpane);
//        window.add(textarea1);


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
     *
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
                textarea1.setText("Bestand niet gevonden of werkt niet.\n" +
                        "Selecteer een (ander) bestand of controlleer de inhoud.");
            }

            //Sets the inputseq to uppercase in case it's not
            sequence = sequence.toUpperCase();
        }
    }
}