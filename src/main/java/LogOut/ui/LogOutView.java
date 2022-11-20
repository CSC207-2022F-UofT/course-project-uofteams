package LogOut.ui;

import LogOut.interface_adapters.LogOutController;
import LogOut.use_case.LogOutInputBoundary;
import login.interface_adapters.LogInController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogOutView extends JPanel implements ActionListener {

    private final LogOutController controller;

    public LogOutView(LogOutController controller){
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String logOut = "";

        if (e.getActionCommand().equals("Log Out")){
            logOut = "Log Out";
        }

        LogOutController logOutRequest = new LogOutController(logOut);
        controller.LogOutInitializer(logOutRequest);


        // I'm not sure if this is the correct way to set the main page view to the Main login/signup view
        CardLayout cardLayout = (CardLayout) this.getLayout();
        if (e.getSource().equals("logOutButton")){
            cardLayout.show(this, "Main View");
        }
    }
}
