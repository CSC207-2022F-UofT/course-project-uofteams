package filter_post.use_case;

import entities.Post;

import java.util.List;

public interface FilterPostDsGateway {
    /**
     * Return a list of posts from the repository.
     */
    List<Post> getPosts();
}
