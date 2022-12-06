package make_comment.use_case;
/*
 * Interface takes data and attempts to create a new comment
 *
 * */
public interface MakeCommentInputBoundary {
    /**
     * save the post to the db, throw errors if necessary, and switch back to the main view upon success.
     * @param requestModel a class containing the data that is used to create the comment.
     */
    void constructAndSaveCommentAndUpdatePost(MakeCommentRequestModel requestModel);

    /**
     * get the current user's id from the CurrentUser entity.
     * @return the current user's id
     */
    int getCurrentUserID();

    /**
     * @return the number of comments created so far.
     */
    int getNumCommentCreated();

}