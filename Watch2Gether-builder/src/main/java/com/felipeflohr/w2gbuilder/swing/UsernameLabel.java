package com.felipeflohr.w2gbuilder.swing;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;

class UsernameLabel extends JLabel {

    private final JTextField usernameTxtField;

    UsernameLabel() {
        setLayout(new FlowLayout());

        JLabel textLabel = new JLabel("Insert username:");

        usernameTxtField = new JTextField(20);
        usernameTxtField.setText("...");
        usernameTxtField.setEditable(true);

        add(textLabel);
        add(usernameTxtField);
    }

    String getUsernameTxtField() {
        return usernameTxtField.getText();
    }
}
