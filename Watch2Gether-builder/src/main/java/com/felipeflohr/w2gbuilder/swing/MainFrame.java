package com.felipeflohr.w2gbuilder.swing;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.GridLayout;

public class MainFrame extends JFrame {

    public MainFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        FileLabel fileLabel = new FileLabel();
        UsernameLabel usernameLabel = new UsernameLabel();
        NotWorkingVideosLabel notWorkingVideosLabel = new NotWorkingVideosLabel();

        add(fileLabel);
        add(usernameLabel);
        add(notWorkingVideosLabel);
        add(new BuildButton(usernameLabel, fileLabel, notWorkingVideosLabel));

        setTitle("2flps' Watch2Gether builder");
        setResizable(false);
        setLocationRelativeTo(null);
        setSize(new Dimension(650, 200));
        setVisible(true);
    }
}
