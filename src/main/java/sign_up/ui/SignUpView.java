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


public class SignUpView extends JPanel implements ActionListener, PropertyChangeListener {
    private final SignUpController signUpController;
    private final JTextField editTextEmail = new JTextField("");
    private final JTextField editTextPassword = new JTextField("");
    private final JTextField editTextAdmin = new JTextField("");
    private final BackButton backButton = new BackButton();

    /**
     * Create a Graphical User Interface (GUI) which takes input data from the user
     * @param controller The SignUpController for the use case
     */
    public SignUpView(SignUpController controller) {
        this.signUpController = controller;

        JButton inputButton = new JButton("Register");
        inputButton.addActionListener(this);

        this.setLayout(new GridLayout(6,3, 0, 1));

        JLabel emptyLabel = new JLabel();
        this.add(emptyLabel);
        this.add(emptyLabel);
        this.add(backButton);

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

    /**
    * React to a button click and start the Sign Up use case
    *  @param e The event to be processed
    */
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

    /**
     * Update the GUI, showing an error message or changing to the main view, if signing up is
     * successful, respectively
     * @param evt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("creation failure")) {

            JFrame errorFrame = new JFrame("Error");
            switch ((String) evt.getNewValue()) {
                case ("empty field"):
                    JOptionPane.showMessageDialog(errorFrame, "Email or Password was left blank.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    break;
                case ("incorrect email"):
                    JOptionPane.showMessageDialog(errorFrame, "A proper Utoronto email address was not entered.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    break;
                case ("email exists"):
                    JOptionPane.showMessageDialog(errorFrame, "That email already exists. Try signing in!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    break;
                case ("admin password"):
                    JOptionPane.showMessageDialog(errorFrame, "The admin password is incorrect. " +
                                    "If you do not want to be admin, leave it blank.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }
    }

    /**
     * A nested class of SignUpView which displays a button for switching between sections
     * on the landing
     */
    private class BackButton extends JPanel implements ActionListener{
        private final PropertyChangeSupport observable;

        /**
         * Initialize a new BackButton, creating the GUI
         */
        public BackButton() {
            this.observable = new PropertyChangeSupport(this);

            JButton backButton = new JButton("Back");
            backButton.addActionListener(this);

            this.add(backButton);
        }

        /**
         * Add a PropertyChangeListener to observe the BackButton
         * @param observer the observer which will observe the BackButton
         */
        public void addObserver(PropertyChangeListener observer) {
            this.observable.addPropertyChangeListener("back button", observer);
        }

        /**
         * Respond to the BackButton being clicked by updating its observers
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            observable.firePropertyChange("back button", false, true);
        }
    }

    /**
     * Add a PropertyChangeListener to observe the BackButton
     * @param observer the observer which will observe the BackButton
     */
    public void addObserver(PropertyChangeListener observer) {
        this.backButton.addObserver(observer);
    }
}

