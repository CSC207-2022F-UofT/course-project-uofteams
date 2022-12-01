package filter_post.use_case;

public interface FilterPostOutputBoundary {
    /**
     * Update the viewable posts and notify any observers.
     * @param filteredPosts A FilterPostResponseModel object storing the filtered posts.
     */
    void updateViewablePosts(FilterPostResponseModel filteredPosts);
}
