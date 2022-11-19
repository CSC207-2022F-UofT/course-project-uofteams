package makePost.use_case;

import entities.Post;

import java.util.List;
import java.util.Map;

public interface MakePostInputBoundary {
    void makePost(MakePostRequestModel mprm);
    int getNumPostsCreated();
    Map<String, String> getCurrentUser();
    List<Map<String, Object>> getFavouritedUsers();

    List<Map<String, Object>> getReplies();

    int getCurrentPostID();

    String getCreationDate();
}
