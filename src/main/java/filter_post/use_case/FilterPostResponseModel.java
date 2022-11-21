package filter_post.use_case;

/**
 * A class that stores the output data for this use case.
 */
public class FilterPostResponseModel {
    private final String[][] filteredPosts;

    /**
     * Instantiate a default FilterPostResponseModel object to store the output data for this use case.
     *
     * @param newFilteredPosts A list of filtered posts with the following data for each (sorted in this
     *                         order): postID, title, post description.
     */
    public FilterPostResponseModel(String[][] newFilteredPosts) {
        this.filteredPosts = newFilteredPosts;
    }

    /**
     * Returns a list of the filtered posts stored in this FilterPostResponseModel object.
     */
    public String[][] getFilteredPosts() {
        return filteredPosts;
    }
}
