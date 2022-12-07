package make_post.use_case;

import entities.Post;

import java.time.LocalDate;
import java.util.List;

public class PostFactory {
    /**
     * creates a post entity.
     * @param poster id of the user who posted this post.
     * @param title title of the post.
     * @param mainDesc main description of the post.
     * @param tags tags of the post.
     * @param collaborators description of the user's ideal collaborators.
     * @param deadline deadline upon which this post is to be deleted.
     * @param numPostsCreated the number of posts created so far.
     * @return a post with data input by the user.
     */
    public Post create(int poster, String title, String mainDesc, List<String> tags, String collaborators, LocalDate deadline,
                       int numPostsCreated){
        return new Post(poster, title, mainDesc, tags, collaborators, deadline, numPostsCreated);
    }
}
