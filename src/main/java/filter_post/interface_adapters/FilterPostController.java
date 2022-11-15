package filter_post.interface_adapters;

import filter_post.use_case.FilterPostInputBoundary;
import filter_post.use_case.FilterPostRequestModel;

import java.util.List;

public class FilterPostController {
    private final FilterPostRequestModel currentTags;
    private final FilterPostInputBoundary interactor;

    /**
     * Initialize a FilterPostController object.
     * @param currentTags   A FilterPostRequestModel object that stores the current filters
     * @param interactor    A FilterPostInputBoundary object that specifies the functionality of the use case.
     */
    public FilterPostController(FilterPostRequestModel currentTags, FilterPostInputBoundary interactor) {
        this.currentTags = currentTags;
        this.interactor = interactor;
    }

    /**
     * Calls upon the abstraction of the use case to filter the posts.
     * @param filters A list of tags to filter the posts by.
     */
    public void filter(List<String> filters) {
        currentTags.setFilterTags(filters);
        interactor.filterPosts(currentTags);
    }
}
