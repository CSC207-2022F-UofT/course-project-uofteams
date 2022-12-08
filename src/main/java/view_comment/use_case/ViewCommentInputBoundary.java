package view_comment.use_case;

public interface ViewCommentInputBoundary {
    /**
     * Grab all comments of given Post
     * @param postId The tags to filter the posts by.
     */
    void retrieveComments(ViewCommentRequestModel postId);

}
