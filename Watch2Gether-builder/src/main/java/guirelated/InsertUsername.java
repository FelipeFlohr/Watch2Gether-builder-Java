package guirelated;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.io.File;

public class InsertUsername extends JLabel {

    JFrame mainFrame;
    public static JTextField text;
    private static File txtFile;

    InsertUsername(JFrame mainFrame){
        this.mainFrame = mainFrame;

        this.setLayout(new FlowLayout());

        var insertText = new JTextField(15);
        insertText.setText("Insert username: ");
        insertText.setEditable(false);

        text = new JTextField(20);
        text.setText("...");
        text.setEditable(true);

        this.add(insertText);
        this.add(text);

        mainFrame.add(this);
    }

}
