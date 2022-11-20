package makePost.use_case;

import entities.Post;

import java.util.List;
import java.util.Map;

public interface MakePostDataAccessInterface {
    int getNumberOfPosts();
    void setNumberOfPosts(int newNumPostsCreated);
    void savePost(Map<String, String> postAttributes);
    int getCurrentUser();
    List<Map<String, Object>> getFavouritedUsers();

    List<Map<String, Object>> getReplies();

    int getCurrentPostID();

    String getCreationDate();

    void setTags(int postID, String tags);
}
