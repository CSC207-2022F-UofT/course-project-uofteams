package filter_post.use_case;

/**
 * A class that stores the input data for this use case.
 */
public class FilterPostRequestModel {
    private final String[] filterTags;

    /**
     * Instantiate a default FilterPostRequestModel object to store input data for this use case.
     */
    public FilterPostRequestModel(String[] newFilterTags) {
        this.filterTags = newFilterTags;
    }

    /**
     * Return a list of tags stored in this FilterPostRequestModel.
     */
    public String[] getFilterTags() { return this.filterTags; }
}
