package make_comment.use_case;

public class MakeCommentRequestModel {
    //MakeCommentRequestModel is a class that stores input data for make comment use case

    // The text body of the comment
    private final String body;

    // id of the post this comment is associated to
    private final int postId;


    /**
     * Initializes a MakeCommentRequestModel
     *
     * @param body String representation comment body
     * @param postId String representation post this comment belongs to

     */
    public MakeCommentRequestModel(String body, int postId) {
        this.body = body;
        this.postId = postId;
    }

    public String getCommentBody(){
        return this.body;
    }

    public int getPostId(){
        return this.postId;
    }
}