package afv1;
import javax.swing.*;
import java.awt.*;

public class op3 {
    public static void main(String[] args) {
        // Frame creation
        JFrame gui = new JFrame();
        gui.setVisible(true);
        gui.setDefaultCloseOperation(3);
        gui.setSize(500, 500);

        JLabel labeltje = new JLabel("Ik ben een label");
        gui.add(labeltje);
        JTextField tekstblok = new JTextField(4);
        gui.add(tekstblok);
        gui.setLayout(new FlowLayout());
    }

}
