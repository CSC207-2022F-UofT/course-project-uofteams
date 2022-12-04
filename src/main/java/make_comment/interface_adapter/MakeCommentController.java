package make_comment.interface_adapter;

import make_comment.use_case.MakeCommentInputBoundary;
import make_comment.use_case.MakeCommentRequestModel;

public class MakeCommentController {
    private final MakeCommentInputBoundary interactor;

    public MakeCommentController(MakeCommentInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void passToInteractor(String body, int postId){

        //pass input info to interactor

        MakeCommentRequestModel makeCommRequestModel = new
                MakeCommentRequestModel(body, postId);
        interactor.constructAndSaveCommentAndUpdatePost(makeCommRequestModel);
    }
}