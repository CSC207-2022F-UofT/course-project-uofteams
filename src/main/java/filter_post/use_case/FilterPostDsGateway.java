package filter_post.use_case;

import java.util.List;

public interface FilterPostDsGateway {
    /**
     * Return a list of posts from the repository.
     */
    List<String[]> getPosts();

    /**
     * Return a list of IDs of the posts the Current User has favourited
     */
    List<Integer> getFavourites();

    /**
     * Return a list of IDs of the posts the Current User has made
     */
    List<Integer> getMyPosts();

}
