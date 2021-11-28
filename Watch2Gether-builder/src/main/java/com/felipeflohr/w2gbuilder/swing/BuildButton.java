package com.felipeflohr.w2gbuilder.swing;

import com.felipeflohr.w2gbuilder.seleniumbuilder.Builder;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

class BuildButton extends JButton implements ActionListener {

    private final UsernameLabel USERNAME;
    private final FileLabel TXT_VIDEO_LINKS_PATH;
    private final NotWorkingVideosLabel NOT_WORKING_VIDEOS_LABEL;

    BuildButton(UsernameLabel username, FileLabel txtVideoLinksPath, NotWorkingVideosLabel notWorkingVideosLabel) {
        USERNAME = username;
        TXT_VIDEO_LINKS_PATH = txtVideoLinksPath;
        NOT_WORKING_VIDEOS_LABEL = notWorkingVideosLabel;

        setText("Build Watch2Gether!");
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final boolean NOT_WORKING_VIDEOS_ENABLED = NOT_WORKING_VIDEOS_LABEL.getTxtFile() != null;
        final boolean ANY_NULL_VALUE = TXT_VIDEO_LINKS_PATH.getTxtFile() == null || USERNAME.getUsernameTxtField() == null;

        if (ANY_NULL_VALUE) {
            JOptionPane.showMessageDialog(null, "Please select a valid .txt file and/or a valid username", "Error:", JOptionPane.WARNING_MESSAGE);
        } else if (TXT_VIDEO_LINKS_PATH.getTxtFile().getAbsolutePath().equals("")
                || USERNAME.getUsernameTxtField().equals("...")
                || USERNAME.getUsernameTxtField().equals("..")
                || USERNAME.getUsernameTxtField().equals(".")) {
            JOptionPane.showMessageDialog(null, "Please select a valid .txt file and/or a valid username", "Error:", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                new Builder(TXT_VIDEO_LINKS_PATH.getTxtFile(), USERNAME.getUsernameTxtField(), NOT_WORKING_VIDEOS_ENABLED, NOT_WORKING_VIDEOS_LABEL.getTxtFile());
            } catch (FileNotFoundException | InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
