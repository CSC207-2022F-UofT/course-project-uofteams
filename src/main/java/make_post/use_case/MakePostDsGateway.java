package make_post.use_case;

import make_post.use_case.make_post_exceptions.MakePostException;

import java.util.Map;

public interface MakePostDsGateway {
    int getNumberOfPosts();
    void setNumberOfPosts(int newNumPostsCreated);
    void savePost(Map<String, String> postAttributes);
    int getCurrentUser() throws MakePostException;
}
