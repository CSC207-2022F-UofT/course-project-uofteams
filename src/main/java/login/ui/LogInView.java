package login.ui;

import login.interface_adapters.LogInController;
import login.interface_adapters.LogInViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInView extends JPanel implements ActionListener {

    private final LogInViewModel viewModel;

    private final LogInController controller;

    private final JTextArea enterEmail = new JTextArea("");
    private final JTextArea enterPassword = new JTextArea("");


    public LogInView(LogInController controller, LogInViewModel viewModel){
        this.controller = controller;
        this.viewModel = viewModel;


        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel textLabel = new JLabel("Log In");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridx = 5;
        constraints.gridy = 6;
        this.add(textLabel, constraints);

        JLabel email = new JLabel("Email");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridx = 3;
        constraints.gridy = 8;
        this.add(email, constraints);


        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridx = 3;
        constraints.gridy = 10;
        this.add(enterEmail, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridx = 3;
        constraints.gridy = 12;
        this.add(enterPassword, constraints);

        JButton logInButton = new JButton("Log In");
        logInButton.addActionListener(this);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridx = 3;
        constraints.gridy = 14;
        this.add(logInButton, constraints);

        this.setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        String emailInput = enterEmail.getText();
        String passwordInput = enterPassword.getText();

        enterEmail.setText(" ");
        enterPassword.setText(" ");

        controller.logInInitializer(emailInput, passwordInput);
        if (viewModel.getLogInSuccess()){
            // call main view
        } else {
            JFrame error = new JFrame("ERROR");
            error.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Container errors = error.getContentPane();

            JLabel errorLabel = new JLabel();
            String errorMessage = viewModel.getLogInError();

            switch (errorMessage) {
                case "Empty Email or Password":
                    errorLabel.setText("Empty Email or Password");
                    break;
                case "Incorrect Email":
                    errorLabel.setText("Incorrect Email");
                    break;
                case "Incorrect Password":
                    errorLabel.setText("Incorrect Password");
                    break;
            }

        errors.setLayout(new BorderLayout());
        errorLabel.setPreferredSize(new Dimension(100, 100));
        errors.add(errorLabel, BorderLayout.CENTER);

        error.pack();
        error.setVisible(true);
        }
    }

}