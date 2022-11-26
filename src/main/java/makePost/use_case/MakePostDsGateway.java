package makePost.use_case;

import java.util.Map;

public interface MakePostDsGateway {
    int getNumberOfPosts();
    void setNumberOfPosts(int newNumPostsCreated);
    void savePost(Map<String, String> postAttributes);
    int getCurrentUser() throws MakePostException;
}
