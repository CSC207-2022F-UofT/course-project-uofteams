package viewpost.ui;

import javax.swing.*;

public class MainPostView {
    private JFrame mainpostview;
    private ViewPostView viewPost;
    private PostListView postList;

    public MainPostView(){
        this.mainpostview = new JFrame();
        this.mainpostview.setSize(900,800);
        // adding view post view
        this.viewPost = new ViewPostView();
        this.mainpostview.add(this.viewPost.getViewPostPanel());
        this.viewPost.getViewPostPanel().setBounds(300,120,600,680);
        // adding post list view
        this.postList = new PostListView();
    }

}
