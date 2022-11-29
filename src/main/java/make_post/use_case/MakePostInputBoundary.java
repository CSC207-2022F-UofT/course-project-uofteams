package make_post.use_case;

public interface MakePostInputBoundary {
    /**
     * save the post to the db, throw errors if necessary, and switch back to the main view upon success.
     * @param requestModel a class containing the data that is used to create the post.
     */
    void makePost(MakePostRequestModel requestModel);

    /**
     * @return the number of posrs created so far.
     */
    int getNumPostsCreated();

    /**
     * get the current user's id from the CurrentUser entity.
     * @return the current user's id
     */
    int getCurrentUser();
}
