package com.felipeflohr.w2gbuilder.guirelated;

import com.felipeflohr.w2gbuilder.seleniumrelated.BuildW2G;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class BuildW2GBtn extends JButton implements ActionListener {

    JFrame mainFrame;

    BuildW2GBtn(JFrame mainFrame){
        this.mainFrame = mainFrame;

        this.setText("Build Watch2Gether!");
        this.addActionListener(this);

        mainFrame.add(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        BuildW2G w2g = new BuildW2G(SelectTxtFile.getTxtFile(), InsertUsername.text.getText());
        try {
            w2g.build();
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
