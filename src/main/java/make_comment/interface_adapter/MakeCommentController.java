package make_comment.interface_adapter;

import make_comment.use_case.MakeCommentInputBoundary;
import make_comment.use_case.MakeCommentRequestModel;

public class MakeCommentController {
    private final MakeCommentInputBoundary interactor;

    /**
     * This is the controller for the use case. It passes the information for making a comment to the interactor.
     * @param interactor The interactor for the use case.
     */

    public MakeCommentController(MakeCommentInputBoundary interactor) {
        this.interactor = interactor;
    }


    /**
     * passes the input made by the user to make the comment to the interactor.
     * @param body the attributes of the comment inputted by the user.
     * @param postId the attributes of the comment inputted by the user.
     */
    public void passToInteractor(String body, int postId){

        //pass input info from View to interactor

        MakeCommentRequestModel makeCommRequestModel = new
                MakeCommentRequestModel(body, postId);
        interactor.constructAndSaveCommentAndUpdatePost(makeCommRequestModel);
    }
}