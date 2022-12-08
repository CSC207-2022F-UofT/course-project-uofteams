package delete_post.use_case;

import entities.Post;

public interface DeletePostDsGateway {

    /**
     * Retrieve post from database
     * @param postId ID of post
     * @return Post object
     */
    Post getPostDelete(int postId);

    /**
     * Delete post id from a user's favourite list
     * @param postId ID of post to be deleted
     * @param userId ID of user to delete from
     */
    void removeFavourite(int postId, int userId);

    /**
     * Delete post ID from its user's post list
     * @param postId ID of post to be deleted
     * @param userId ID of user to delete from
     */
    void removeUser(int postId, int userId);

    /**
     * Delete row containing post in post csv
     * @param postId ID of post to be deleted
     */
    void deletePost(int postId);

    /**
     * Delete row containing comment in comment csv
     * @param commentId ID of comment to be deleted
     */
    void deleteComment(int commentId);
}
