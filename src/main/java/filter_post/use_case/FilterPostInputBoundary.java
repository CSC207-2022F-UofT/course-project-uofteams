package filter_post.use_case;

public interface FilterPostInputBoundary {
    /**
     * Filter the posts to be displayed by the tags given in filters.
     * @param filters The tags to filter the posts by.
     */
    void filterPosts(FilterPostRequestModel filters);
}
