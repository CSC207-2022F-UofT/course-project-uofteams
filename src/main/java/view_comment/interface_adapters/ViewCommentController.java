package view_comment.interface_adapters;

import view_comment.use_case.ViewCommentInputBoundary;
import view_comment.use_case.ViewCommentRequestModel;

public class ViewCommentController {

    /**
     * Initializes a ViewCommentInputBoundary object.
     */
    private final ViewCommentInputBoundary interactor;

    public ViewCommentController(ViewCommentInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Execute this use case.
     * @param postId The id of the post we are trying to grab comments for.
     */
    public void viewComment(int postId){

        //pass input info to interactor

        ViewCommentRequestModel viewCommentRequestModel = new
                ViewCommentRequestModel(postId);
        interactor.retrieveComments(viewCommentRequestModel);
    }
}