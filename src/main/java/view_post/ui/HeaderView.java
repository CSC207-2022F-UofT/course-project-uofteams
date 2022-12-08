package view_post.ui;

import log_out.ui.LogOutView;
import make_post.ui.MakePostView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The header of the main UI of UofTeams.
 */
public class HeaderView implements ActionListener {
    // The JPanel that contains all visual elements in the header
    private final JPanel headerPanel;
    private final MakePostButton makePost = new MakePostButton("New Post");
    private final MakePostView makePostView;

    /**
     * Initializes HeaderView.
     * The HeaderView does not get updated during the same session.
     * @param partialPath A String object that contains the path (excluding the file name) to the folder of
     *                    images used in this program
     */
    public HeaderView(String partialPath, MakePostView makePostView, LogOutView logOutView){
        this.makePostView = makePostView;
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
        ImageIcon logoImg = new ImageIcon(partialPath + "logo.png");
        Image image = logoImg.getImage(); // transform it
        Image newImg = image.getScaledInstance(14, 4,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        logoImg = new ImageIcon(newImg);  // transform it back
        JLabel logo = new JLabel();
        logo.setIcon(logoImg);
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
        makePost.addActionListener(this);
        c.gridwidth = 1;
        c.gridx = 2;
        c.gridy = 1;
        this.headerPanel.add(makePost, c);

        //add logout button, update later to integrate with logout UC
        c.gridwidth = 1;
        c.gridx = 3;
        c.gridy = 1;
        this.headerPanel.add(logOutView, c);

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(makePost)) {
            // Removed these for now as trying a different approach
            // Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            // double width = screenSize.getWidth();
            // double height = screenSize.getHeight();
            JOptionPane.showMessageDialog(null, makePostView, "Make Post", JOptionPane.PLAIN_MESSAGE);
        }
    }

    public static class MakePostButton extends JButton {
        /*
         * Initialize a SignUpButton instance by calling super
         *
         * @param text The name of the button
         * */
        public MakePostButton(String text) {
            super(text);
        }
    }
}
