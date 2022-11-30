package filter_post.interface_adapters;

import filter_post.use_case.FilterPostInputBoundary;
import filter_post.use_case.FilterPostRequestModel;

public class FilterPostController {
    private final FilterPostInputBoundary interactor;

    /**
     * Initialize a FilterPostController object.
     * @param interactor    A FilterPostInputBoundary object that specifies the functionality of the use case.
     */
    public FilterPostController(FilterPostInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Calls upon the abstraction of the use case to filter the posts.
     * @param filters A list of tags to filter the posts by.
     */
    public void filter(String[] filters) {
        FilterPostRequestModel inputData = new FilterPostRequestModel(filters);

        interactor.filterPosts(inputData);
    }
}
