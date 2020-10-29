//package guitest2;
//
///*
//Meerdere regels commentaar
// */
//
///**
// * Dit is java doc commentaar, autodoc generatie
// */
//
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class workworkwork {
//    private JButton chooseFileButton;
//    private JTextField textField1;
//    private JPanel panel;
//
//    public static void main(String[] args) {
//        JFrame frame = new JFrame("workworkwork");
//        frame.setContentPane(new workworkwork().panel);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);
//    }
//
//    public workworkwork() {
//        chooseFileButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                File selectedFile;
//                fileChooser = new JFileChooser();
//                int reply = fileChooser.showOpenDialog(null);
//                if (reply == JFileChooser.APPROVE_OPTION) {
//                    selectedFile = fileChooser.getSelectedFile();
//                    textField1.setText(selectedFile.getAbsolutePath());
//                }
//            }
//        });
//    }
//}
