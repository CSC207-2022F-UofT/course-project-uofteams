package filter_post.use_case;

import java.util.List;

public interface FilterPostDsGateway {
    /**
     * Return a list of posts from the repository.
     */
    List<String[]> getPosts();
}
