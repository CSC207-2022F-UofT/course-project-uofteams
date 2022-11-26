package log_out.ui;

import log_out.interface_adapters.LogOutController;
import log_out.interface_adapters.LogOutControllerData;

import javax.swing.*;
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
            LogOutControllerData data = new LogOutControllerData(logOut);
            controller.logOutInitializer(data);


            // will need to implement main view which contains the 'Log Out' button
            // and then switch that view with the Main LogIn/SignUp view
            // cardLayout.show(this, "Main View");
        }
    }
}
