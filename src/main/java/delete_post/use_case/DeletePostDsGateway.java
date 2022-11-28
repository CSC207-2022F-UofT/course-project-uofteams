package delete_post.use_case;

import java.util.List;

public interface DeletePostDsGateway {

    void removeFavourite(int postId, int userId);
    void removeTag(int postId, String tag);
    void deletePost(int postId);
}
