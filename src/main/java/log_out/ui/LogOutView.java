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

    public JButton logOutButton = new JButton("logOutButton");

    private JPanel MasterLandingView;



    public LogOutView(LogOutController controller){
        this.controller = controller;

        logOutButton.addActionListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("Log Out")){
            CardLayout cardLayout = new CardLayout();
            cardLayout.show(MasterLandingView, "");
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
