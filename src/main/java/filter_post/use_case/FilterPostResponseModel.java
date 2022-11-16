package filter_post.use_case;

import entities.Post;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that stores the output data for this use case.
 */
public class FilterPostResponseModel {
    private final List<Post> filteredPosts;

    /**
     * Instantiate a default FilterPostResponseModel object to store the output data for this use case.
     */
    public FilterPostResponseModel(List<Post> newFilteredPosts) {
        this.filteredPosts = new ArrayList<>(newFilteredPosts);
    }

    /**
     * Returns a list of the filtered posts stored in this FilterPostResponseModel object.
     */
    public List<Post> getFilteredPosts() {
        return filteredPosts;
    }
}
