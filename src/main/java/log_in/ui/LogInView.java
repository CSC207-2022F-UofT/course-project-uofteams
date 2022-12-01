package log_in.ui;

import log_in.interface_adapters.LogInController;
import log_in.interface_adapters.LogInControllerData;

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
        this.add(new BackButton());

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

        LogInControllerData input = new LogInControllerData(emailInput, passInput);

        logInController.logInInitializer(input);

    }

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
            errorFrame.setVisible(true);
        }
    }

    private static class BackButton extends JPanel implements ActionListener{
        private final PropertyChangeSupport observable;

        public BackButton(){
            this.observable = new PropertyChangeSupport(this);

            JButton backButton = new JButton("Back");
            backButton.addActionListener(this);

            this.add(backButton);
        }

        public void addObserver(PropertyChangeListener observer){
            this.observable.addPropertyChangeListener("back button", observer);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            observable.firePropertyChange("back button", false, true);
        }
    }

    public void addObserver(PropertyChangeListener observer){
        this.backButton.addObserver(observer);
    }
}