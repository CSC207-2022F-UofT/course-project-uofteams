package make_comment.use_case;
/*
 * Interface takes data and attempts to create a new comment
 *
 * */
public interface MakeCommentInputBoundary {
    void constructAndSaveComment(MakeCommentRequestModel requestModel);
    int getCurrentUserID();
    int getNumCommentCreated();

    void updatePost(int postId);

}