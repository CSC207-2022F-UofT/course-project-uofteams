package view_post.ui;

import javax.swing.*;
import java.awt.*;

/**
 * The header of the main UI of UofTeams.
 */
public class HeaderView {
    // The JPanel that contains all visual elements in the header
    private final JPanel headerPanel;

    /**
     * Initializes HeaderView.
     * The HeaderView does not get updated during the same session.
     * @param partialPath A String object that contains the path (excluding the file name) to the folder of
     *                    images used in this program
     */
    public HeaderView(String partialPath){
        // setting up this.headerPanel
        this.headerPanel = new JPanel();
        this.headerPanel.setPreferredSize(new Dimension(900, 120));
        this.headerPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // adding buffer
        JLabel buffer1 = new JLabel();
        c.gridwidth = 4;
        c.gridx = 0;
        c.gridy = 0;
        this.headerPanel.add(buffer1, c);

        //adding the logo to the header
        ImageIcon logoimg = new ImageIcon(partialPath + "logo.png");
        JLabel logo = new JLabel();
        logo.setIcon(logoimg);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        this.headerPanel.add(logo, c);

        //adding buffer
        JLabel buffer2 = new JLabel();
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 1;
        this.headerPanel.add(buffer2, c);

        //add make post button, update later to integrate with make post UC
        JButton makepost = new JButton("New Post");
        c.gridwidth = 1;
        c.gridx = 2;
        c.gridy = 1;
        this.headerPanel.add(makepost, c);

        //add logout button, update later to integrate with logout UC
        JButton logout = new JButton("Log Out");
        c.gridwidth = 1;
        c.gridx = 3;
        c.gridy = 1;
        this.headerPanel.add(logout, c);

        // adding buffer
        JLabel buffer3 = new JLabel();
        c.gridwidth = 4;
        c.gridx = 0;
        c.gridy = 2;
        this.headerPanel.add(buffer3, c);
    }

    public JPanel getHeader(){
        return headerPanel;
    }

}
