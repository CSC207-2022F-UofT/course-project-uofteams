package view_post.ui;


import favourite.ui.FavouriteView;

import make_comment.ui.MakeCommentView;
import view_post.interface_adapters.ViewPostOutputData;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The view of that displays the post the user selected to view
 */
public class ViewPostView extends JPanel implements PropertyChangeListener {

    private final FavouriteView favouriteView;
    private final MakeCommentView makeCommentView;


    /**
     * Initializes ViewPostView.
     * ViewPostView displays the default message when it is first initialized and displayed to the user when they log in
     */

    public ViewPostView (FavouriteView favouriteView, MakeCommentView makeCommentView){
        this.favouriteView = favouriteView;
        this.makeCommentView = makeCommentView;


        this.setPreferredSize(new Dimension(600, 680));

        // displaying default message
        this.setLayout(new BorderLayout());
        JLabel defaultMessage = new JLabel("Please select a post to view!");
        this.add(defaultMessage, BorderLayout.CENTER);
    }

    /**
     * Displays a new post when a post is selected in the PostListView.
     * @param event A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent event){
        if("show error".equals(event.getPropertyName())){
            JFrame errorFrame = new JFrame("Error");
            JOptionPane.showMessageDialog(errorFrame, event.getNewValue(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        if ("show post".equals(event.getPropertyName())) {
            this.displayPost((ViewPostOutputData) event.getNewValue());
        }
    }

    /**
     * Clears the panel of all visual elements.
     */
    private void clearPanel(){this.removeAll();}

    /**
     * Default view when the view is first called or when there are no posts to show.
     * We may not need this method depending on how the rest of the use cases operate.
     */
    public void refresh(){
        this.clearPanel();
        this.setLayout(new BorderLayout());
        JLabel defaultMessage = new JLabel("Please select a post to view!");
        this.add(defaultMessage, BorderLayout.CENTER);
    }

    /**
     * Adds GUI elements for the main post being viewed.
     * @param outputData A ViewPostOutputData object with all the information about the post being displayed
     */
    private void displayPost(ViewPostOutputData outputData) {
        // clearing panel
        this.clearPanel();

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // adding title
        JTextArea title = new JTextArea();
        title.setText(outputData.getTitle());
        title.setEditable(false);
        title.setLineWrap(false);
        title.setFont(new Font("Serif", Font.BOLD, 20));
        c.gridwidth = 3;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 0;
        this.add(title, c);

        // adding blank buffer
        JLabel buffer1 = new JLabel();
        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridx = 2;
        c.gridy = 0;
        this.add(buffer1, c);

        // adding favourite button, update later to integrate with favourite uc

        this.favouriteView.setPostID(outputData.getPostID());

        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridx = 3;
        c.gridy = 0;

        this.add(this.favouriteView, c);


        // adding blank buffer
        JLabel buffer2 = new JLabel();
        c.gridwidth = 2;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 1;
        this.add(buffer2, c);

        // adding tags
        JLabel tags;
        if (outputData.getPostTags() == ""){
            tags = new JLabel("Tags: None");
        } else{
            tags = new JLabel("Tags: " + outputData.getPostTags());
        }
        c.gridwidth = 2;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 2;
        this.add(tags, c);

        // adding poster email
        JLabel posterEmail = new JLabel("Post by: " + outputData.getPosterEmail());
        c.gridwidth = 2;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 3;
        this.add(posterEmail, c);

        // adding collaborators
        JLabel collab;
        if (outputData.getCollaborators() == ""){
            collab = new JLabel("Collaborators: None");
        } else{
            collab = new JLabel("Collaborators: " + outputData.getCollaborators());
        }
        c.gridwidth = 2;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 4;
        this.add(collab, c);

        // adding creation date
        JLabel creationDate = new JLabel("Post created on: " + outputData.getCreationDate());
        c.gridwidth = 2;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 5;
        this.add(creationDate, c);

        // adding expiry date
        JLabel deadline = new JLabel("Post expires on: " + outputData.getDeadline());
        c.gridwidth = 2;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 6;
        this.add(deadline, c);

        // adding buffer
        JLabel buffer3 = new JLabel();
        c.gridwidth = 2;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 7;
        this.add(buffer3, c);

        // adding main description
        JTextArea body = new JTextArea(outputData.getPostBody());
        body.setEditable(false);
        body.setLineWrap(true);
        JScrollPane description = new JScrollPane(body);
        description.setPreferredSize(new Dimension(300,400));
        c.gridwidth = 3;
        c.gridheight = 4;
        c.gridx = 0;
        c.gridy = 8;
        this.add(description, c);

        // adding comment button, update later to integrate with view/post comment UC

        this.makeCommentView.setPostID(outputData.getPostID());

        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridx = 1;
        c.gridy = 14;

        this.add(this.makeCommentView, c);


        // adding delete post button, update later to integrate with delete UC
        JButton delete = new JButton("Delete Post");
        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridx = 1;
        c.gridy = 15;
        this.add(delete, c);
    }

}
