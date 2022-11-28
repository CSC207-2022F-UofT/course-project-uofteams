package make_comment.interface_adapter;

import make_comment.use_case.MakeCommentOutputBoundary;
import make_comment.use_case.MakeCommentResponseModel;

public class makeCommentPresenter implements MakeCommentOutputBoundary {
    private final makeCommentViewModel viewModel;

    public makeCommentPresenter(makeCommentViewModel viewModel){
        this.viewModel = viewModel;
    }

    @Override
    public void present(MakeCommentResponseModel responseModel) {
        if (responseModel.isCreationSuccess()) {
            viewModel.updateViewModel(responseModel.isCreationSuccess(),
                    responseModel.getErrorMessage());
        } else {
            viewModel.updateViewModel(responseModel.isCreationSuccess(),
                    responseModel.getErrorMessage());
        }

    }
}