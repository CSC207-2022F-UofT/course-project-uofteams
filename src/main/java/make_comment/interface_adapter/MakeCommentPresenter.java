package make_comment.interface_adapter;

import make_comment.use_case.MakeCommentOutputBoundary;
import make_comment.use_case.MakeCommentResponseModel;

public class MakeCommentPresenter implements MakeCommentOutputBoundary {
    private final MakeCommentViewModel viewModel;

    /**
     * Initialises the presenter for this use case.
     * @param viewModel viewModel for this use case.
     */

    public MakeCommentPresenter(MakeCommentViewModel viewModel){
        this.viewModel = viewModel;
    }

    /**
     * updates the view model.
     * @param responseModel a data class that contains information whether the comment creation was a success.
     */

    @Override
    public void present(MakeCommentResponseModel responseModel) {
            viewModel.updateViewModel(responseModel.isCreationSuccess(),
                    responseModel.getErrorMessage());


    }
}