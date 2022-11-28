package view_post.use_case;

public interface ViewPostInputBoundary {
    /**
     * Display the selected post.
     * @param requestModel The input data from the user.
     */
    void displayPost(ViewPostRequestModel requestModel);
}
