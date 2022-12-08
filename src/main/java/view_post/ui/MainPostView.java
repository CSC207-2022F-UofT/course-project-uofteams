package view_post.ui;

import javax.swing.*;
import java.awt.*;

/**
 * The main view of UofTeams.
 */
public class MainPostView extends JPanel{

    /**
     * Initializes MainPostView
     * @param viewPost A ViewPostView object that displays the "main post" or the post being "viewed"
     * @param postList A PostListView object that displays the posts the user can choose to view
     * @param header A HeaderView object that contains the JPanel with the header UI elements
     */
    public MainPostView(ViewPostView viewPost, PostListView postList, HeaderView header){
        // adding header view
        this.setLayout(new BorderLayout());

        header.getHeader().setBounds(0, 0, 900, 120);

        // adding view post view
        // A JPanel that holds the header, main post, and list of posts
        viewPost.setBounds(300,120,600,680);

        // adding post list view
        postList.setBounds(0,120,300,680);


        this.setSize(900, 800);
        this.add(header.getHeader(), BorderLayout.NORTH);
        this.add(viewPost, BorderLayout.EAST);
        this.add(postList, BorderLayout.WEST);
    }

}
