package guirelated;

import javax.swing.JFrame;
import java.awt.*;

public class Frame extends JFrame {

    public static InsertUsername username;

    public Frame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(600, 200));
        this.setLayout(new GridLayout(4, 1));

        new SelectTxtFile(this);
        username = new InsertUsername(this);
        new BuildW2GBtn(this);

        this.setTitle("2flps - Watch2Gether builder");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
