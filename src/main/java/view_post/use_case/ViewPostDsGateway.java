package view_post.use_case;

import view_post.use_case.view_post_exceptions.PostDoesNotExistException;

/**
 * This is the interface implemented by the Database Access
 */
public interface ViewPostDsGateway {
    /**
     * Return a list of strings representing the data of the post with matching post IDs.
     */
    String[] getPostInfo(int postID) throws PostDoesNotExistException;
}
