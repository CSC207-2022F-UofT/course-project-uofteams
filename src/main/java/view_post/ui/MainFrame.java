package view_post.ui;

import sign_up.ui.MasterLandingView;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/*
* The main frame which will hold the UI for the system
* It will observe the SignUpViewModel, LogInViewModel, LogOutViewModel
*
* */
public class MainFrame extends JFrame implements PropertyChangeListener {
    final static String MAIN = "Main View";
    final static String LANDING = "Landing View";
    final public Container contentPane;

    /*
    * Initialize the card view
    * @ param the landingView and the postView
    * */
    public MainFrame(MasterLandingView landingView, MainPostView postView){
        super("UofTeams");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        contentPane = this.getContentPane();
        contentPane.setLayout(new CardLayout());

        contentPane.add(landingView, LANDING);
        contentPane.add(postView, MAIN);
    }

    /*
    * Responds from property change events that are sent from the Sign In, Log In, and Log Out use cases
    *
    * @param the property change event to respond to
    * */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("creation success")) {
            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            cardLayout.show(contentPane, MAIN);
        } else if (evt.getPropertyName().equals("Log Out")) {
            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            cardLayout.show(contentPane, LANDING);
        } else if (evt.getPropertyName().equals("Login Success")) {
            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            cardLayout.show(contentPane, MAIN);
        }
    }

    public void setUp() {
        this.pack();
        this.setVisible(true);
    }
}
