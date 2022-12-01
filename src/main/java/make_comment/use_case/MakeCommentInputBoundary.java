package make_comment.use_case;
/*
 * Interface takes data and attempts to create a new comment
 *
 * */
public interface MakeCommentInputBoundary {
    void constructAndSaveCommentAndUpdatePost(MakeCommentRequestModel requestModel);
    int getCurrentUserID();
    int getNumCommentCreated();

}