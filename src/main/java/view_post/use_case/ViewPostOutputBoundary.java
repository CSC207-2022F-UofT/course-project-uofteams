package view_post.use_case;

/**
 * The interface implemented by the presenter of the View Post use case.
 */
public interface ViewPostOutputBoundary {
    /**
     * Update the currently viewed post and notify any observers.
     * @param responseModel A ViewPostResponseModel object storing the post data.
     */
    void updateActivePost(ViewPostResponseModel responseModel);
}
