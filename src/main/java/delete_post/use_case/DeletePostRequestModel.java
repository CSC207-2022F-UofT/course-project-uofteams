package delete_post.use_case;

import entities.CurrentUser;
import entities.User;

public class DeletePostRequestModel {
    private int postId;
    private boolean isTimer;

    public DeletePostRequestModel(int postId, boolean isTimer){
        this.postId = postId;
        this.isTimer = isTimer;
    }

    public int getPostId(){
        return this.postId;
    }
    public boolean getIsTimer(){
        return this.isTimer;
    }
}
