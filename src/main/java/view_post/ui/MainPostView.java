package view_post.ui;

import javax.swing.*;

/**
 * The main view of UofTeams.
 */
public class MainPostView {
    // A JPanel that holds the header, main post, and list of posts
    private JPanel mainPostView;
    private ViewPostView viewPost;
    private PostListView postList;
    private HeaderView header;

    /**
     * Initializes MainPostView
     * @param viewPost A ViewPostView object that displays the "main post" or the post being "viewed"
     * @param postList A PostListView object that displays the posts the user can choose to view
     * @param header A HeaderView object that contains the JPanel with the header UI elements
     */
    public MainPostView(ViewPostView viewPost, PostListView postList, HeaderView header){
        // adding header view
        this.header = header;
        this.header.getHeader().setBounds(0, 0, 900, 120);

        // adding view post view
        this.viewPost = viewPost;
        this.viewPost.setBounds(300,120,600,680);

        // adding post list view
        this.postList = postList;
        this.postList.setBounds(0,120,300,680);

        this.mainPostView = new JPanel();
        this.mainPostView.setSize(900, 800);
        this.mainPostView.add(header.getHeader());
        this.mainPostView.add(this.viewPost);
        this.mainPostView.add(this.postList);
    }

    /**
     * Returns the mainPostView JPanel which contains all UI elements of the main UofTeams view
     * @return A JPanel object
     */
    public JPanel getMainPostView(){return this.mainPostView;}

}
