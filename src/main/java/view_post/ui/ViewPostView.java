package view_post.ui;

import view_post.interface_adapters.View;
import view_post.interface_adapters.ViewPostViewModel;

import javax.swing.*;
import java.awt.*;

public class ViewPostView implements View {
    private final JPanel viewpostpanel;

    /**
     * Initializes ViewPostView
     */
    public ViewPostView (){
        this.viewpostpanel = new JPanel();
        this.viewpostpanel.setSize(600, 680);
    }

    /**
     * Adds GUI elements for the main post being viewed onto the viewpostpanel (JPanel).
     * @param viewModel
     */
    @Override
    public void display(ViewPostViewModel viewModel) {
        this.viewpostpanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // adding title
        JTextArea title = new JTextArea();
        // title.setText(viewModel.getTitle()); we need to add title
        title.setEditable(false);
        title.setLineWrap(true);
        title.setFont(new Font("Serif", Font.BOLD, 20));
        c.gridwidth = 2;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 0;
        this.viewpostpanel.add(title, c);

        // adding blank buffer
        JLabel buffer1 = new JLabel();
        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridx = 2;
        c.gridy = 0;
        this.viewpostpanel.add(buffer1, c);

        // adding favourite button
        FavouritePostView favouriteButton = new FavouritePostView(viewModel.getPostID());
        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridx = 3;
        c.gridy = 0;
        this.viewpostpanel.add(favouriteButton, c);

        // adding blank buffer
        JLabel buffer2 = new JLabel();
        c.gridwidth = 2;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 1;
        this.viewpostpanel.add(buffer2, c);

        // adding tags
        JLabel tags;
        if (viewModel.getPostTags() == ""){
            tags = new JLabel("Tags: None");
        } else{
            tags = new JLabel("Tags: " + viewModel.getPostTags());
        }
        c.gridwidth = 2;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 2;
        this.viewpostpanel.add(tags, c);

        // adding poster email
        JLabel posterEmail = new JLabel("Post by: " + viewModel.getPosterEmail());
        c.gridwidth = 2;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 3;
        this.viewpostpanel.add(posterEmail, c);

        // adding collaborators
        JLabel collab;
        if (viewModel.getCollaborators() == ""){
            collab = new JLabel("Collaborators: None");
        } else{
            collab = new JLabel("Collaborators: " + viewModel.getCollaborators());
        }
        c.gridwidth = 2;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 4;
        this.viewpostpanel.add(collab, c);

        // adding creation date
        JLabel creationDate = new JLabel("Post created on: " + viewModel.getCreationDate());
        c.gridwidth = 2;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 5;
        this.viewpostpanel.add(creationDate, c);

        // adding expiry date
        JLabel deadline = new JLabel("Post expires on: " + viewModel.getDeadline());
        c.gridwidth = 2;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 6;
        this.viewpostpanel.add(deadline, c);

        // adding buffer
        JLabel buffer3 = new JLabel();
        c.gridwidth = 2;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 7;
        this.viewpostpanel.add(buffer3, c);

        // adding main description
        JTextArea body = new JTextArea(viewModel.getPostBody());
        body.setEditable(false);
        body.setLineWrap(true);
        JScrollPane description = new JScrollPane(body);
        description.setPreferredSize(new Dimension(300,400));
        c.gridwidth = 3;
        c.gridheight = 4;
        c.gridx = 0;
        c.gridy = 8;
        this.viewpostpanel.add(description, c);

        // adding view comment button, update later to integrate with view/post comment UC
        JButton viewcomments = new JButton("View Comments");
        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridx = 1;
        c.gridy = 14;
        this.viewpostpanel.add(viewcomments, c);

        // adding delete post button, update later to integrate with delete UC
        JButton delete = new JButton("Delete Post");
        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridx = 1;
        c.gridy = 15;
        this.viewpostpanel.add(delete, c);
    }

    public JPanel getViewPostPanel(){
        return this.viewpostpanel;
    }

}
