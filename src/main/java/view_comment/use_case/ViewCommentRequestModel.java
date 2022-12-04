package view_comment.use_case;

public class ViewCommentRequestModel {
//MakeCommentRequestModel is a class that stores input data for make comment use case

    // id of the post we are trying for retrieve replies for
    private final int postId;


    /*
     * Initializes a ViewCommentRequestModel
     *
     * @param postId: id of the post we are trying for retrieve replies for
     */
    public ViewCommentRequestModel(int postId) {
        this.postId = postId;
    }


    public int getPostId() {
        return this.postId;
    }
}