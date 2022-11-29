package make_post.use_case;

import make_post.use_case.make_post_exceptions.MakePostException;

import java.util.Map;

public interface MakePostDsGateway {
    /**
     * get the number of posts from the db.
     * @return number of posts from the db.
     */
    int getNumberOfPosts();

    /**
     * update the number of posts in the db.
     * @param newNumPostsCreated the updated number of posts.
     */
    void setNumberOfPosts(int newNumPostsCreated);

    /**
     * save a post to the db.
     * @param postAttributes the attributes of that post to be saved (title, etc.).
     */
    void savePost(Map<String, String> postAttributes);

    /**
     * @return current user's ID from db
     */
    int getCurrentUser();
}
