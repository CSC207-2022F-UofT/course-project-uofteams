package LogOut.ui;

import LogOut.interface_adapters.LogOutController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LogOutView extends JPanel implements PropertyChangeListener {

    private final LogOutController controller;

    public LogOutView(LogOutController controller){
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("Log Out")){
            String logOut = "Log Out";
            LogOutController logOutController = new LogOutController(logOut);
            controller.LogOutInitializer(logOutController);


            CardLayout cardLayout = (CardLayout) this.getLayout();
            cardLayout.show(this, "Main View");
        }
    }
}
