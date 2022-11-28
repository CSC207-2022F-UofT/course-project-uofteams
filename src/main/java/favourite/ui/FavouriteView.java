package favourite.ui;

import favourite.interface_adapters.FavouriteController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The FavouriteView class in the UI layer implements View and ActionListener interfaces.
 * Whenever a user interacts with the favourite button, this class runs the Favourite use case.
 */
public class FavouriteView extends JPanel implements ActionListener, PropertyChangeListener {
    // the controllwe that gets triggered when a user interacts with the FavouriteView
    private final FavouriteController controller;
    // the id of the post being favourited/unfavourited
    private final int postid;

    /**
     * Initializes FavouriteView
     *
     * @param postid id of the post being favourited/unfavourited
     */
    public FavouriteView(int postid, FavouriteController controller){
        this.controller = controller;
        this.postid = postid;
        JButton favbutton = new JButton("Favourite");
        this.add(favbutton);
        favbutton.addActionListener(this);
    }

    /**
     * Calls the controller when the "Favourite" button is pressed by the user
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        controller.favourite(this.postid);
    }

    /**
     * Creates a pop-up JFrame that tells the user whether the post has been successfully "favourited" or "unfavourited"
     *
     * @param event A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent event){
        JFrame updateFrame = new JFrame();
        updateFrame.setVisible(true);
        if (event.getPropertyName().equals("favourited")){
            JOptionPane.showMessageDialog(updateFrame, "This post has been successfully added to " +
                    "your favourites!");
        }else if (event.getPropertyName().equals("unfavourited")){
            JOptionPane.showMessageDialog(updateFrame, "This post has been removed from your favourites.");
        }
    }

}
