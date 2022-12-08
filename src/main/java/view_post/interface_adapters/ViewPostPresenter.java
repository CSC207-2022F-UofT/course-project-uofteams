package view_post.interface_adapters;

import view_post.use_case.ViewPostOutputBoundary;
import view_post.use_case.ViewPostResponseModel;

/**
 * The presenter class of the View Post use case which implements the ViewPostOutputBoundary.
 */
public class ViewPostPresenter implements ViewPostOutputBoundary {
    // the view model of this use case
    private final ViewPostViewModel viewModel;

    /**
     * Initializes ViewPostPresenter
     * @param viewModel A ViewPostViewModel object
     */
    public ViewPostPresenter(ViewPostViewModel viewModel){ this.viewModel = viewModel; }

    /**
     * Transfers data from the input (a ViewPostResponseModel object) to a ViewPostOutputData object
     * so that it can be displayed in the view by calling the updateView method in the view model.
     * @param responseModel A ViewPostResponseModel object storing the post data.
     */
    @Override
    public void updateActivePost(ViewPostResponseModel responseModel) {
        // transferring data from a ViewPostResponseModel object to a ViewPostOutputData object
        String postEmail = responseModel.getPosterEmail();
        String postBody = responseModel.getPostBody();
        String postTags = responseModel.getPostTags();
        String deadline = responseModel.getDeadline();
        String creationDate = responseModel.getCreationDate();
        String collaborators = responseModel.getCollaborators();
        int postID = responseModel.getPostID();
        String title = responseModel.getTitle();

        ViewPostOutputData outputData = new ViewPostOutputData(postEmail, postBody, postTags, deadline, creationDate,
                collaborators, postID, title);

        // firing property change to update the view
        viewModel.updateView(outputData);
    }
}
