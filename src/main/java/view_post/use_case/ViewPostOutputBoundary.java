package view_post.use_case;

public interface ViewPostOutputBoundary {
    /**
     * Update the currently viewed post and notify any observers.
     * @param responseModel A ViewPostResponseModel object storing the post data.
     */
    void updateActivePost(ViewPostResponseModel responseModel);
}
