package view_post.use_case;

/**
 * A data structure class for storing the input data.
 */
public class ViewPostRequestModel {
    private final int postID;

    /**
     * Initialize a ViewPostRequestModel.
     * @param postID The unique ID of the post the user wants to view.
     */
    public ViewPostRequestModel(int postID) {
        this.postID = postID;
    }

    /**
     * Return the ID of the post the user wants to view.
     */
    public int getPostID() {
        return this.postID;
    }
}
