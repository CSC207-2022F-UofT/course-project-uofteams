package delete_post.use_case;

public class DeletePostRequestModel {
    private final int postId;
    private final boolean isTimer;

    /**
     * Initialize DeletePostRequestModel object
     * @param postId ID of post to be deleted
     * @param isTimer true if deletion request made by timer use case
     */
    public DeletePostRequestModel(int postId, boolean isTimer){
        this.postId = postId;
        this.isTimer = isTimer;
    }

    /**
     * get post Id of post to be deleted
     * @return post ID
     */
    public int getPostId(){
        return this.postId;
    }

    /**
     * check if deletion was requested by timer
     * @return true if requested by timer
     */
    public boolean getIsTimer(){
        return this.isTimer;
    }
}