package delete_post.interface_adapters;

import delete_post.use_case.DeletePostOutputBoundary;
import delete_post.use_case.DeletePostResponseModel;
import delete_post.UI.DeleteErrorView;

public class DeletePostPresenter implements DeletePostOutputBoundary{

    private final DeletePostViewModel viewModel;
    private final Jframe postView;

    public DeletePostPresenter(DeletePostViewModel viewModel){
        this.viewModel = viewModel;
    }
    @Override
    public void prepareFailView(){
        DeleteErrorView(postView);
    }
    public void prepareSuccessView(DeletePostResponseModel responseModel){
        viewModel.updateViewModel();
    }
}
