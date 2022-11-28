package delete_post.use_case;

import entities.CurrentUser;
import entities.User;

public class DeletePostRequestModel {
    int postId;
    private boolean isAdmin = CurrentUser.getIsAdmin();
    private User currentUser = CurrentUser.getCurrentUser();
    private boolean isTimer;

    public DeletePostRequestModel(int postId, boolean isTimer){
        this.postId = postId;
        this.isTimer = isTimer;
    }

    public int getPostId(){
        return this.postId;
    }
    public int getUserId(){
        return this.currentUser.getId();
    }
    public boolean getIsAdmin(){
        return this.isAdmin;
    }
    public boolean getIsTimer(){
        return this.isTimer;
    }
}
