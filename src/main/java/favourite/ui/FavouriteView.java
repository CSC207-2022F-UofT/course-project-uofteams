package favourite.ui;

import favourite.interface_adapters.FavouriteController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The view of the Favourite use case.
 */
public class FavouriteView extends JPanel implements ActionListener, PropertyChangeListener {
    // the controller that gets triggered when a user interacts with the FavouriteView
    private final FavouriteController controller;
    // the id of the post being favourited/unfavourited
    private int postId;

    /**
     * Initializes FavouriteView
     *
     * @param controller A FavouriteController object
     */
    public FavouriteView(FavouriteController controller){
        this.controller = controller;
        this.postId = -1;
        JButton favButton = new JButton("Favourite");
        this.add(favButton);
        favButton.addActionListener(this);
    }

    /**
     * Updates the postID instance variable so that FavouriteView know which post it is on.
     * This method id called in the ViewPostView every time it is refreshed with a new post.
     *
     * @param postId the integer ID of the post that it is being displayed on
     */
    public void setPostID(int postId){
        this.postId = postId;
    }

    /**
     * Calls the controller when the "Favourite" button is pressed by the user
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        controller.favourite(this.postId);
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
        updateFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        if (event.getPropertyName().equals("favourited")){
            JOptionPane.showMessageDialog(updateFrame, "This post has been successfully added to " +
                    "your favourites!");
        }else if (event.getPropertyName().equals("unfavourited")){
            JOptionPane.showMessageDialog(updateFrame, "This post has been removed from your favourites.");
        }
    }

}
