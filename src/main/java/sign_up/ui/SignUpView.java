package sign_up.ui;

import sign_up.interface_adapters.SignUpController;
import sign_up.interface_adapters.SignUpUserInputData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public class SignUpView extends JPanel implements ActionListener {
    private final SignUpController signUpController;
    private final JTextField editTextEmail = new JTextField("");
    private final JTextField editTextPassword = new JTextField("");
    private final JTextField editTextAdmin = new JTextField("");

    /*
     * A part of the landing view which is responsible for taking user data to sign up and passing it on
     * @param controller The controller which will start the use case
     */
    public SignUpView(SignUpController controller) {
        this.signUpController = controller;

        JButton inputButton = new JButton("Register");
        inputButton.addActionListener(this);

        this.setLayout(new GridLayout(6,3, 0, 1));

        JLabel emptyLabel = new JLabel();
        this.add(emptyLabel);
        this.add(emptyLabel);
        this.add(new BackButton());

        this.add(emptyLabel);

        JLabel topLabel = new JLabel("Register New User");
        this.add(topLabel);

        this.add(emptyLabel);

        JLabel userLabel = new JLabel("Enter your Utoronto Email:");
        this.add(userLabel);

        this.add(emptyLabel);

        this.add(editTextEmail);

        JLabel passLabel = new JLabel("Enter a Password:");
        this.add(passLabel);

        this.add(emptyLabel);

        this.add(editTextPassword);

        JLabel adminLabel = new JLabel("Leave this field blank:");
        this.add(adminLabel);

        this.add(emptyLabel);
        this.add(editTextAdmin);
        this.add(emptyLabel);
        this.add(inputButton);
    }

    /*
    * React to a button click and start the Sign Up use case
    *
    *  @param e The event to be processed
    * */
    @Override
    public void actionPerformed(ActionEvent e) {
        String emailInput = editTextEmail.getText();
        String passInput = editTextPassword.getText();
        String adminInput = editTextAdmin.getText();

        editTextEmail.setText("");
        editTextPassword.setText("");
        editTextAdmin.setText("");

        SignUpUserInputData inputData = new SignUpUserInputData(emailInput, passInput, adminInput);

        signUpController.signUp(inputData);
    }

    private static class BackButton extends JPanel implements ActionListener{
        private final PropertyChangeSupport observable;

        public BackButton() {
            this.observable = new PropertyChangeSupport(this);

            JButton backButton = new JButton("Back");
            backButton.addActionListener(this);

            this.add(backButton);
        }

        public void addObserver(PropertyChangeListener observer) {
            this.observable.addPropertyChangeListener("back button", observer);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            observable.firePropertyChange(new PropertyChangeEvent(this, "go back",
                    "", ""));
        }
    }
}

