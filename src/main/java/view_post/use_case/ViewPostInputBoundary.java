package view_post.use_case;

public interface ViewPostInputBoundary {
    /**
     * Displays the selected post.
     * @param requestModel The input data from the user.
     */
    void displayPost(ViewPostRequestModel requestModel);
}
