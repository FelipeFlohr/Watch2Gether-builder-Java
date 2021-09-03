package guirelated;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SelectTxtFile extends JLabel implements ActionListener {

    JFrame mainFrame;
    JButton button;
    JTextField text;
    private static File txtFile;

    public SelectTxtFile(JFrame mainFrame){
        this.mainFrame = mainFrame;

        this.setLayout(new FlowLayout());

        button = new JButton("Select .txt file containing the links...");
        button.addActionListener(this);

        text = new JTextField(20);
        text.setEditable(false);

        this.add(button);
        this.add(text);

        mainFrame.add(this);
    }

    public static File getTxtFile() {
        return txtFile;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button){
            FileNameExtensionFilter fileFilter = new FileNameExtensionFilter(".txt file", "txt");
            JFileChooser chooseTxt = new JFileChooser();

            chooseTxt.setFileFilter(fileFilter);
            int answer = chooseTxt.showOpenDialog(null);
            if(answer == JFileChooser.APPROVE_OPTION){
                File selection = chooseTxt.getSelectedFile();

                if(selection.exists()){
                    txtFile = chooseTxt.getSelectedFile();
                    text.setText(getTxtFile().getAbsolutePath());
                }
            }
        }
    }
}
