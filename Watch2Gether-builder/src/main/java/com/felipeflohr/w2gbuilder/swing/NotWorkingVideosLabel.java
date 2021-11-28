package com.felipeflohr.w2gbuilder.swing;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static javax.swing.JFileChooser.APPROVE_OPTION;

class NotWorkingVideosLabel extends JLabel implements ActionListener {

    private File txtFile = null;
    private final JTextField filePathTxtField;

    NotWorkingVideosLabel() {
        setLayout(new FlowLayout());

        filePathTxtField = new JTextField(20);
        filePathTxtField.setEditable(false);

        JButton selectFileBtn = new JButton("Select .txt file to insert non-working videos (optional)...");
        selectFileBtn.addActionListener(this);

        add(selectFileBtn);
        add(filePathTxtField);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        FileNameExtensionFilter fileFilter = new FileNameExtensionFilter(".txt file", "txt");
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setFileFilter(fileFilter);
        int answer = fileChooser.showOpenDialog(null);
        if (answer == APPROVE_OPTION) {
            File fileSelected = fileChooser.getSelectedFile();

            if (fileSelected.exists()) {
                txtFile = fileChooser.getSelectedFile();
                filePathTxtField.setText(txtFile.getAbsolutePath());
            }
        }
    }

    File getTxtFile() {
        return txtFile;
    }
}
