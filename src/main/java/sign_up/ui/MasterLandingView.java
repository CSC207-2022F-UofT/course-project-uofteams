package sign_up.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class MasterLandingView extends JPanel implements PropertyChangeListener {
    private JLabel imageLabel = new JLabel(new ImageIcon("src/main/resources/logo1.png"));
    private JPanel mainPanel = new JPanel();
    private JPanel signUpView;
    private JPanel logInView;
    final static String MAIN = "Main View";
    final static String SIGNUP = "Sign Up View";
    final static String LOGIN = "Log In View";
    private ButtonView buttonView = new ButtonView();

    /*
    * Initializes an instance of MasterLandingView and sets up its panels
    * */
    public MasterLandingView(SignUpView signUpView, JPanel logInView) {
        this.signUpView = signUpView;
        this.logInView = logInView;


        this.setLayout(new BorderLayout());

        this.add(imageLabel, BorderLayout.NORTH);
        this.add(mainPanel, BorderLayout.CENTER);

        mainPanel.setLayout(new CardLayout());

        mainPanel.add(buttonView, MAIN);
        mainPanel.add(signUpView, SIGNUP);
        mainPanel.add(logInView, LOGIN);

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("back button")) {
            CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
            cardLayout.show(mainPanel, "Main View");
        }
    }

    public class ButtonView extends JPanel implements ActionListener {
        private final SignUpButton signUpButton = new SignUpButton("Sign Up");
        private final LogInButton logInButton = new LogInButton("Log In");
        /*
        * Initializes a new instance of ButtonView and sets up its buttons and action listener
        * */
        public ButtonView() {
            signUpButton.addActionListener(this);
            logInButton.addActionListener(this);

            this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

            this.add(logInButton);
            this.add(Box.createRigidArea(new Dimension(5,0)));
            this.add(signUpButton);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
            if (e.getSource().equals(signUpButton)) {
                cardLayout.show(mainPanel, "Sign Up View");
            } else {
                cardLayout.show(mainPanel, "Log In View");
            }
        }

        /*
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getPropertyName().equals("go back")) {
                CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
                cardLayout.show(mainPanel, "Main View");
            }
        }

         */
    }

    public ButtonView getButtonView() {
        return this.buttonView;
    }

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
