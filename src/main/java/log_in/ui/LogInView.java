package log_in.ui;

import log_in.interface_adapters.LogInController;
import log_in.interface_adapters.LogInUserInputData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LogInView extends JPanel implements ActionListener, PropertyChangeListener {
    private final LogInController logInController;
    private final JTextField enterEmail = new JTextField("");
    private final JTextField enterPassword = new JTextField("");
    private final BackButton backButton = new BackButton();

    /**
     * the log in view that takes users input data and passes it to the controller
     * @param controller the controller that will start the use case
     */
    public LogInView(LogInController controller){
        this.logInController = controller;

        JButton logInButton = new JButton("Log In");
        logInButton.addActionListener(this);

        this.setLayout(new GridLayout(5,3,0,1));

        JLabel emptyLabel = new JLabel();
        this.add(emptyLabel);
        this.add(backButton);

        this.add(emptyLabel);

        JLabel topLabel = new JLabel("Log In to your Account");
        //topLabel.setFont();
        // color and size;
        this.add(topLabel);

        this.add(emptyLabel);

        JLabel emailLabel = new JLabel("Enter your Email");
        this.add(emailLabel);

        this.add(emptyLabel);
        this.add(enterEmail);

        JLabel passLabel = new JLabel("Enter your Password");
        this.add(passLabel);

        this.add(emptyLabel);
        this.add(enterPassword);
        this.add(emptyLabel);
        this.add(logInButton);
    }

    /**
     * When the log_in button is clicked pass the info to controller to start the LogIn Use Case
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String emailInput = enterEmail.getText();
        String passInput = enterPassword.getText();

        enterEmail.setText("");
        enterPassword.setText("");

        LogInUserInputData input = new LogInUserInputData(emailInput, passInput);

        logInController.logInInitializer(input);

    }

    /**
     * Update the UI with an error message or changing the view to the ViewPost view if a successful LogIn has occurred
     * @param evt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("Login Failure")){
            JFrame errorFrame = new JFrame("ERROR");
            switch ((String) evt.getNewValue()){
                case ("Empty Email or Password"):
                    JOptionPane.showMessageDialog(errorFrame, "Email Or Password are empty.",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                    break;
                case ("Incorrect Email"):
                    JOptionPane.showMessageDialog(errorFrame,"Email is not attached to any User.",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                    break;
                case ("Incorrect Password"):
                    JOptionPane.showMessageDialog(errorFrame, "Incorrect Password. Please Try Again.",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }
    }

    /**
     * Nested class of LogInView that displays a button to return to the MasterView
     */
    private static class BackButton extends JPanel implements ActionListener{
        private final PropertyChangeSupport observable;

        /**
         * Initialize a back button
         */
        public BackButton(){
            this.observable = new PropertyChangeSupport(this);

            JButton backButton = new JButton("Back");
            backButton.addActionListener(this);

            this.add(backButton);
        }

        /**
         * Add a PropertyChangeListener to the back button to observe it
         * @param observer the observer which will observe the back button
         */
        public void addObserver(PropertyChangeListener observer){
            this.observable.addPropertyChangeListener("back button", observer);
        }

        /**
         * Respond to a back button being clicked by updating observers
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            observable.firePropertyChange("back button", false, true);
        }
    }

    /**
     * This will add a PropertyChangeListener to observe the back button
     * @param observer the observer that will observe the back button
     */
    public void addObserver(PropertyChangeListener observer){
        this.backButton.addObserver(observer);
    }
}