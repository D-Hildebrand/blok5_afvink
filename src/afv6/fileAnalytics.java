package afv6;

/**
 * @Author dominic
 */

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;

public class fileAnalytics extends JFrame implements ActionListener {

    private JLabel label1;
    private JButton button1, button2;
    private JTextField textfield1;
    private JTextArea textarea1;
    private JPanel panel;


    public static void main(String[] args) {
        fileAnalytics frame = new fileAnalytics();
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

        //Adding descriptive label and adding it
        label1 = new JLabel("Deze app telt het aantal eiwitten en " +
                "geeft het percentage hydrofobe / hydrofiele aminozuren weer.");
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
        textarea1.setText("Hier komen de resultaten in staan");
        window.add(textarea1);


        //Setting and deploying panel for drawing
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(600, 200));
        panel.setBackground(Color.lightGray);
        window.add(panel);


    }


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
            String protseq = "";

            //Checking if the inputfile works or not,
            //if it does work, creates string with the file's content
            try {
                Scanner filereader = new Scanner(new File(String.valueOf(inputfile)));
                while (filereader.hasNextLine()) {
                    protseq = protseq.concat(filereader.nextLine());
                }

            } catch (FileNotFoundException exception) {
                textarea1.setText("Bestand niet gevonden of werkt niet, selecteer een ander bestand.");
            }

            protseq = protseq.toUpperCase();


            //Polair and apolair amino acids + counter
            Set<String> apol = Set.of("G", "I", "L", "M", "F", "P", "W", "V", "A");
            Set<String> pol = Set.of("Q", "N", "H", "S", "T", "Y", "C", "R", "D", "E", "K");
            int apolcount = 0;
            int polcount = 0;
            int protseqlen = protseq.length();

            //for iterating file
            for (int i = 0; i < protseqlen; i++) {
                String aminoacid = String.valueOf(protseq.charAt(i));
                if (pol.contains(aminoacid)){
                    polcount++;
                }
                else if (apol.contains(aminoacid)) {
                    apolcount++;
                }
                //In case unknown, places unknown symbol in app and stops running.
                else {
                    textarea1.setText("Onbekend symbool gevonden: '"+aminoacid+"'" +
                            "\nControlleer bestand en probeer opnieuw");
                    return;
                }
            }

            // making floats for percentages
            float pol_perc = (float)polcount / protseqlen * 100;
            float apol_perc = (float)apolcount / protseqlen * 100;

            // making length for percentage bar visualisation
            int pol_bar = Math.round(pol_perc) * 6;
            int apol_bar = Math.round(apol_perc) * 6;

            textarea1.setText("Het programma is goed verlopen\n" +
                    "Gevonden aminozuren in bestand: "+ protseqlen+"\n" +
                    "Gevonden polaire aminozuren "+ polcount+ " dat is "+pol_perc+"%\n" +
                    "Gevonden apolaire aminozuren "+ apolcount+" dat is "+apol_perc+"%");


            // drawing bars for showing percentage
            Graphics paper = panel.getGraphics();

            paper.setColor(Color.BLUE);
            paper.fillRect(0, 0, pol_bar,100);
            paper.setColor(Color.RED);
            paper.fillRect(0, 100, apol_bar,100);
            paper.setColor(Color.black);
            paper.drawString("Polair ("+pol_perc+"%)",250, 50);
            paper.drawString("Apolair ("+apol_perc+"%)",250, 150);

        }
    }
}
