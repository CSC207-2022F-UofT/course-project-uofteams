package filter_post.use_case;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that stores the input data for this use case.
 */
public class FilterPostRequestModel {
    private final List<String> filterTags;

    /**
     * Instantiate a default FilterPostRequestModel object to store input data for this use case.
     */
    public FilterPostRequestModel(List<String> newFilterTags) {
        this.filterTags = new ArrayList<>(newFilterTags);
    }

    /**
     * Return a list of tags stored in this FilterPostRequestModel.
     */
    public List<String> getFilterTags() { return this.filterTags; }
}
