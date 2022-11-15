package filter_post.use_case;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that stores the input data for this use case.
 */
public class FilterPostRequestModel {
    private List<String> filterTags;

    /**
     * Instantiate a default FilterPostRequestModel object to store input data for this use case.
     */
    public FilterPostRequestModel() {
        this.filterTags = new ArrayList<>();
    }

    /**
     * Update the FilterPostRequestModel to store a new list of tags to filter posts by.
     * @param newFilterTags The new tags to filter posts by.
     */
    public void setFilterTags(List<String> newFilterTags) { this.filterTags = newFilterTags; }

    /**
     * Return a list of tags stored in this FilterPostRequestModel.
     */
    public List<String> getFilterTags() { return this.filterTags; }
}
