package sign_up.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The view for the landing, which the user sees when they open the program. It contains buttons
 * to take the user to log in and sign up screens, and buttons to navigate between these
 */
public class MasterLandingView extends JPanel implements PropertyChangeListener {
    private JLabel imageLabel = new JLabel(new ImageIcon("src/main/resources/logo.png"));
    private JPanel mainPanel = new JPanel();
    private JPanel signUpView;
    private JPanel logInView;
    final static String MAIN = "Main View";
    final static String SIGNUP = "Sign Up View";
    final static String LOGIN = "Log In View";
    private ButtonView buttonView = new ButtonView();

    /**
    * Initializes an instance of MasterLandingView and sets up its panels
    */
    public MasterLandingView(SignUpView signUpView, JPanel logInView) {
        this.signUpView = signUpView;
        this.logInView = logInView;


        this.setLayout(new BorderLayout());

        Image image = ((ImageIcon) imageLabel.getIcon()).getImage(); // transform it
        Image newimg = image.getScaledInstance(544, 160,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageLabel.setIcon(new ImageIcon(newimg));  // transform it back
        this.add(imageLabel, BorderLayout.NORTH);
        this.add(mainPanel, BorderLayout.CENTER);

        mainPanel.setLayout(new CardLayout());

        mainPanel.add(buttonView, MAIN);
        mainPanel.add(signUpView, SIGNUP);
        mainPanel.add(logInView, LOGIN);

    }

    /**
     * React to a FirePropertyChange call by something this class is observing
     * @param evt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("back button")) {
            CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
            cardLayout.show(mainPanel, "Main View");
        }
    }

    /**
     * A class to handle tbe log in and sign up buttons on the main screen
     */
    public class ButtonView extends JPanel implements ActionListener {
        private final SignUpButton signUpButton = new SignUpButton("Sign Up");
        private final LogInButton logInButton = new LogInButton("Log In");
        /*
        * Initializes a new instance of ButtonView and sets up its buttons and action listener
        * */

        /**
         * Initialize a new ButtonView
         */
        public ButtonView() {
            signUpButton.addActionListener(this);
            logInButton.addActionListener(this);

            this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

            this.add(logInButton);
            this.add(Box.createRigidArea(new Dimension(5,0)));
            this.add(signUpButton);
        }

        /**
         * Respond to the Sign Up or Log In buttons being clicked
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
            if (e.getSource().equals(signUpButton)) {
                cardLayout.show(mainPanel, "Sign Up View");
            } else {
                cardLayout.show(mainPanel, "Log In View");
            }
        }

    }

    /**
     * Returns the ButtonView for the landing view
     * @return the ButtonView attribute
     */
    public ButtonView getButtonView() {
        return this.buttonView;
    }

    /**
     * A class which acts as the SignUpButton, responsible for starting the use case
     */
    public static class SignUpButton extends JButton {
        /*
        * Initialize a SignUpButton instance by calling super
        *
        * @param text The name of the button
        * */
        public SignUpButton(String text) {
            super(text);
        }
    }

    /**
     * A class which acts as the log in button, responsible for starting the use case
     */
    public static class LogInButton extends JButton {
        /*
         * Initialize a LogInButton instance by calling super
         *
         * @param text The name of the button
         * */
        public LogInButton(String text) {
            super(text);
        }
    }


}
