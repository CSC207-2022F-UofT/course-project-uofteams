package view_comment.interface_adapters;

import view_comment.use_case.ViewCommentOutputBoundary;
import view_comment.use_case.ViewCommentResponseModel;

public class ViewCommentPresenter implements ViewCommentOutputBoundary {
    private final ViewCommentViewModel viewModel;

    public ViewCommentPresenter(ViewCommentViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void present(ViewCommentResponseModel responseModel) {
        viewModel.updateViewModel(responseModel.isReplies(),
                responseModel.getErrorMessage());


    }
}
