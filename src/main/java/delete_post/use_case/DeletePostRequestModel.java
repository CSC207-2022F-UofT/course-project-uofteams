package delete_post.use_case;

public class DeletePostRequestModel {
    private final int postId;
    private final boolean isTimer;

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
