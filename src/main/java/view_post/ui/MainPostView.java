package view_post.ui;

import javax.swing.*;

public class MainPostView {
    private JPanel mainpostview;
    private ViewPostView viewPost;
    private PostListView postList;
    private HeaderView header;

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

        this.mainpostview = new JPanel();
        this.mainpostview.setSize(900, 800);
        this.mainpostview.add(header.getHeader());
        this.mainpostview.add(this.viewPost);
        this.mainpostview.add(this.postList);
    }

    public JPanel getMainPostView(){return this.mainpostview;}

}
