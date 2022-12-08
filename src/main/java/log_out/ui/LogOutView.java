package log_out.ui;

import log_out.interface_adapters.LogOutController;
import log_out.interface_adapters.LogOutControllerData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LogOutView extends JPanel implements PropertyChangeListener, ActionListener {

    private final LogOutController controller;

    public JButton logOutButton = new JButton("Log Out");

    public LogOutView(LogOutController controller){
        this.controller = controller;

        logOutButton.addActionListener(this);
        this.add(logOutButton);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("Log Out")){
            String logOut = "log out";

            //CardLayout cardLayout = (CardLayout) this.masterView.getLayout;
            //cardLayout.show(this, "Main View");

            // will need the implementation of the main view which contains the 'Log Out' button
            // and then switch that view with the Main LogIn/SignUp view
            // also will need to add a param in my constructor that takes a masterView, so I can call the
            // get layout function in order to show it.
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(logOutButton)) {
            String logOut = "Log Out";
            LogOutControllerData data = new LogOutControllerData(logOut);
            controller.logOutInitializer(data);
        }
    }
}
