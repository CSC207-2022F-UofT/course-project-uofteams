package delete_post.use_case;

public interface DeletePostDsGateway {

    void removeFavourite(int postId, int userId);
    void removeTag(int postId, String tag);
    void deletePost(int postId);
}