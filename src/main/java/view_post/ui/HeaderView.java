package view_post.ui;

import com.sun.net.httpserver.Headers;

import javax.swing.*;
import java.awt.*;

public class HeaderView {
    private final JPanel headerpanel;

    public HeaderView(){
        this.headerpanel = new JPanel();
        this.headerpanel.setSize(900,120);
        this.headerpanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // adding buffer
        JLabel buffer1 = new JLabel();
        c.gridwidth = 4;
        c.gridx = 0;
        c.gridy = 0;
        this.headerpanel.add(buffer1, c);

        //adding the logo to the header
        ImageIcon logoimg = new ImageIcon("src/main/resources/logo.png");
        JLabel logo = new JLabel();
        logo.setIcon(logoimg);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        this.headerpanel.add(logo, c);

        //adding buffer
        JLabel buffer2 = new JLabel();
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 1;
        this.headerpanel.add(buffer2, c);

        //add make post button, update later to integrate with make post UC
        JButton makepost = new JButton("New Post");
        c.gridwidth = 1;
        c.gridx = 2;
        c.gridy = 1;
        this.headerpanel.add(makepost, c);

        //add logout button, update later to integrate with logout UC
        JButton logout = new JButton("Log Out");
        c.gridwidth = 1;
        c.gridx = 3;
        c.gridy = 1;
        this.headerpanel.add(logout, c);

        // adding buffer
        JLabel buffer3 = new JLabel();
        c.gridwidth = 4;
        c.gridx = 0;
        c.gridy = 2;
        this.headerpanel.add(buffer3, c);
    }

    public JPanel getHeader(){
        return headerpanel;
    }

}
