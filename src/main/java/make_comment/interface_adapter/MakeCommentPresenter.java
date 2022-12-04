package make_comment.interface_adapter;

import make_comment.use_case.MakeCommentOutputBoundary;
import make_comment.use_case.MakeCommentResponseModel;

public class MakeCommentPresenter implements MakeCommentOutputBoundary {
    private final MakeCommentViewModel viewModel;

    public MakeCommentPresenter(MakeCommentViewModel viewModel){
        this.viewModel = viewModel;
    }

    @Override
    public void present(MakeCommentResponseModel responseModel) {
            viewModel.updateViewModel(responseModel.isCreationSuccess(),
                    responseModel.getErrorMessage());


    }
}