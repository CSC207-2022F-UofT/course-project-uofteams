package make_comment.interface_adapter;

import make_comment.use_case.MakeCommentInputBoundary;
import make_comment.use_case.MakeCommentRequestModel;

public class makeCommentController {
    private final MakeCommentInputBoundary interactor;

    public makeCommentController(MakeCommentInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void passToInteractor(String body){
        int commentId = interactor.getNumCommentCreated();
        int currentUserID = interactor.getCurrentUserID();
        commentId ++;

        //pass input info to interactor

        MakeCommentRequestModel makeCommRequestModel = new MakeCommentRequestModel(currentUserID, body, commentId);
        interactor.constructAndSaveComment(makeCommRequestModel);
    }
}