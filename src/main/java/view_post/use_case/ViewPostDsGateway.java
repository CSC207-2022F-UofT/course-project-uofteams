package view_post.use_case;

/**
 * This is the interface implemented by the Database Access
 */
public interface ViewPostDsGateway {
    /**
     * Return a list of strings representing the data of the post with matching post IDs.
     */
    String[] getPostInfo(int postID);
}
