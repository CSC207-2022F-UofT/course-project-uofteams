package login.ui;

import login.interface_adapters.LogInController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LogInView extends JPanel implements ActionListener {
    private final LogInController logInController;
    private final JTextField enterEmail = new JTextField("");
    private final JTextField enterPassword = new JTextField("");

    /**
     * the log in view that takes users input data and passes it to the controller
     * @param controller the controller that will start the use case
     */
    public LogInView(LogInController controller){
        this.logInController = controller;

        JButton logInButton = new JButton("Log In");
        logInButton.addActionListener(this);

        this.setLayout(new GridLayout(6,3,0,1));

        JLabel emptyLabel = new JLabel();
        this.add(emptyLabel);
        this.add(new BackButton());

        this.add(emptyLabel);

        JLabel topLabel = new JLabel("Log In to your Account");
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
     * When the login button is clicked pass the info to controller to start the LogIn Use Case
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String emailInput = enterEmail.getText();
        String passInput = enterPassword.getText();

        enterEmail.setText("");
        enterPassword.setText("");

        LogInController input = new LogInController(emailInput, passInput);

        logInController.logInInitializer(input);

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
            observable.firePropertyChange(new PropertyChangeEvent(this, "go back",
                    "", ""));

        }
    }
}