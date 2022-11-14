package sign_up.ui;

import sign_up.drivers.DatabaseAccess;
import sign_up.interface_adapters.SignUpViewModel;
import sign_up.interface_adapters.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SignUpView implements View {

    private final SignUpViewModel viewModel;
    private JButton inputButton = new JButton("Register");
    private final JTextArea editTextEmail = new JTextArea("Enter your utoronto email here");
    private final JTextArea editTextPassword = new JTextArea("Enter a password here");
    private final JTextArea editTextAdmin = new JTextArea("Leave this field blank");

    private String emailInput;
    private String passwordInput;
    private String adminInput;

    public SignUpView() {
        this.viewModel = new SignUpViewModel(new DatabaseAccess(), this);
    }


    private void addComponentsToPane(Container pane) {
        pane.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        // This won't work for some reason - tried multiple tutorials
        ImageIcon image = new ImageIcon("resources/logo.png");
        JLabel imageLabel = new JLabel(image);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridx = 4;
        constraints.gridy = 2;
        pane.add(imageLabel, constraints);

        JLabel textLabel = new JLabel("Register New User");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridx = 5;
        constraints.gridy = 6;
        pane.add(textLabel, constraints);

        textLabel = new JLabel("Email:");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridx = 3;
        constraints.gridy = 8;
        pane.add(textLabel, constraints);

        textLabel = new JLabel("Password:");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridx = 3;
        constraints.gridy = 10;
        pane.add(textLabel, constraints);

        textLabel = new JLabel("Admin Password:");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridx = 3;
        constraints.gridy = 12;
        pane.add(textLabel, constraints);

        inputButton = new JButton("Register");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridx = 5;
        constraints.gridy = 15;
        pane.add(inputButton, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridx = 7;
        constraints.gridy = 8;
        pane.add(editTextEmail, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridx = 7;
        constraints.gridy = 10;
        pane.add(editTextPassword, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridx = 7;
        constraints.gridy = 12;
        pane.add(editTextAdmin, constraints);


    }

    public void view() {
        JFrame frame = new JFrame("UofTeams");

        inputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                emailInput = editTextEmail.getText();
                passwordInput = editTextPassword.getText();
                adminInput = editTextAdmin.getText();

                editTextEmail.setText(" ");
                editTextPassword.setText(" ");
                editTextAdmin.setText(" ");

                viewModel.signUp(emailInput, passwordInput, adminInput);
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addComponentsToPane(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }


    @Override
    public void update(boolean result, String message) {
        if (result) {
            // Somehow call the main view ... not defined yet
        } else {
            JFrame errorFrame = new JFrame("ERROR");
            errorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Container content = errorFrame.getContentPane();
            JLabel errorLabel = new JLabel();

            switch (message) {
                case ("empty field"): {
                    errorLabel.setText("Error: Email or Password was left blank.");
                } case ("incorrect email"): {
                    errorLabel.setText("Error: Email not in utoronto domain.");
                } case ("email exists"): {
                    errorLabel.setText("Error: An account with that email exists.");
                } case ("admin password"): {
                    errorLabel.setText("Error: admin password incorrect. If you do not" +
                            "want to sign in as admin, leave this field blank.");
                }}


            content.setLayout(new BorderLayout());
            errorLabel.setPreferredSize(new Dimension(200, 100));
            content.add(errorLabel, BorderLayout.CENTER);

            errorFrame.pack();
            errorFrame.setVisible(true);

            view();
        }
    }

}
