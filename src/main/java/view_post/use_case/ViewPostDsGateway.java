package view_post.use_case;

public interface ViewPostDsGateway {
    /**
     * Return a list of strings representing the data of the post with matching post IDs.
     */
    String[] getPostInfo(int postID);
}
