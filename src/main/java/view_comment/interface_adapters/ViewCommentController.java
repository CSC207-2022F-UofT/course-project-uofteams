package view_comment.interface_adapters;

import view_comment.use_case.ViewCommentInputBoundary;
import view_comment.use_case.ViewCommentOutputBoundary;
import view_comment.use_case.ViewCommentRequestModel;

public class ViewCommentController {
    private final ViewCommentInputBoundary interactor;

    public ViewCommentController(ViewCommentInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void passToInteractor(int postId){

        //pass input info to interactor

        ViewCommentRequestModel viewCommentRequestModel = new
                ViewCommentRequestModel(postId);
        interactor.retrieveComments(viewCommentRequestModel);
    }
}
