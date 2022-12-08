package view_post.interface_adapters;

import view_post.use_case.ViewPostInputBoundary;
import view_post.use_case.ViewPostRequestModel;

/**
 * The controller class of the View Post use case.
 */
public class ViewPostController {
    private final ViewPostInputBoundary interactor;

    /**
     * Initializes a ViewPostController object.
     * @param interactor    A ViewPostInputBoundary object that executes the use case.
     */
    public ViewPostController(ViewPostInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Execute this use case.
     * @param postID The id of the post the user wishes to view.
     */
    public void viewPost(int postID) {
        ViewPostRequestModel inputData = new ViewPostRequestModel(postID);

        interactor.displayPost(inputData);
    }
}
