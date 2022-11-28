package delete_post.use_case;

public interface DeletePostDsGateway {

    void removeFavourites(int postId);
    void deletePost(int postId);
    int getPostUser(int postId);
}
