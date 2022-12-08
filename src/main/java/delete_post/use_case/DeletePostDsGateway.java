package delete_post.use_case;

import entities.Post;

public interface DeletePostDsGateway {

    Post getPostDelete(int postId);
    void removeFavourite(int postId, int userId);
    void removeUser(int postId, int userId);
    void deletePost(int postId);
    void deleteComment(int commentId);
}
