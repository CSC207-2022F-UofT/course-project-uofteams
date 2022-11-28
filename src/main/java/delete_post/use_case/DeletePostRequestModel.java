package delete_post.use_case;

import entities.CurrentUser;
import entities.User;
import entities.Post;

public class DeletePostRequestModel {
    private Post post;
    private boolean isAdmin = CurrentUser.getIsAdmin();
    private User currentUser = CurrentUser.getCurrentUser();
    private boolean isTimer;

    public DeletePostRequestModel(Post post){
        this.post = post;
        this.isTimer = false;
    }
    public DeletePostRequestModel(Post post, boolean isTimer){
        this.post = post;
        this.isTimer = isTimer;
    }

    public Post getPost(){
        return this.post;
    }
    public User getPostUser(){
        return this.post.getUser();
    }
    public User getUser(){
        return this.currentUser;
    }
    public boolean getIsAdmin(){
        return this.isAdmin;
    }
    public boolean getIsTimer(){
        return this.isTimer;
    }
}
