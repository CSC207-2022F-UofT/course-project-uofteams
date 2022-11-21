package favourite.ui;

import favourite.drivers.DataAccess;
import favourite.interface_adapters.FavouriteController;
import favourite.interface_adapters.FavouriteViewModel;
import favourite.interface_adapters.View;
import favourite.use_case.FavouriteResponseModel;
import favourite.use_case.PostFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The FavouriteView class in the UI layer implements View and ActionListener interfaces.
 * Whenever a user interacts with the favourite button, this class runs the Favourite use case.
 */
public class FavouriteView extends JPanel implements View, ActionListener {
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

    @Override
    public void actionPerformed(ActionEvent e) {
        controller.favourite(this.postid);
    }

    @Override
    public void update(FavouriteResponseModel responseModel){
        // figure out how to present responseModel as a pop up
    }

}
