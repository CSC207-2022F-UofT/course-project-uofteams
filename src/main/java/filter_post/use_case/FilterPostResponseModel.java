package filter_post.use_case;

import entities.Post;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that stores the output data for this use case.
 */
public class FilterPostResponseModel {
    private List<Post> filteredPosts;

    /**
     * Instantiate a default FilterPostResponseModel object to store the output data for this use case.
     */
    public FilterPostResponseModel() {
        this.filteredPosts = new ArrayList<>();
    }


    /**
     * Returns a list of the filtered posts stored in this FilterPostResponseModel object.
     */
    public List<Post> getFilteredPosts() {
        return filteredPosts;
    }

    /**
     * Update the list of filtered posts stored in this FilterPostResponseModel object.
     * @param newFilteredPosts The new list of filtered posts.
     */
    public void setFilteredPosts(List<Post> newFilteredPosts) {
        this.filteredPosts = newFilteredPosts;
    }
}
