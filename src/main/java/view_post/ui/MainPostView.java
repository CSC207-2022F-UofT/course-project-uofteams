package view_post.ui;

import javax.swing.*;

public class MainPostView {
    private JPanel mainpostview;
    private ViewPostView viewPost;
    private PostListView postList;
    private HeaderView header;

    public MainPostView(){
        this.mainpostview = new JPanel();
        this.mainpostview.setSize(900,800);

        // adding header view
        this.header = new HeaderView();
        this.mainpostview.add(header.getHeader());
        this.header.getHeader().setBounds(0, 0, 900, 120);

        // adding view post view
        this.viewPost = new ViewPostView();
        this.mainpostview.add(this.viewPost);
        this.viewPost.setBounds(300,120,600,680);

        // adding post list view
        this.postList = new PostListView();
    }

}
