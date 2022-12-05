package delete_post.interface_adapters;

import delete_post.use_case.DeletePostOutputBoundary;
import delete_post.use_case.DeletePostResponseModel;

public class DeletePostPresenter implements DeletePostOutputBoundary{

    private final DeletePostViewModel viewModel;

    public DeletePostPresenter(DeletePostViewModel viewModel){
        this.viewModel = viewModel;
    }
    @Override
    public void updateViewModel(DeletePostResponseModel responseModel){
        if (responseModel.deleteSuccess()){
            if (!responseModel.getIsTimer()){
                viewModel.updateViewModel(true, false);
            }
        }
        else if (!responseModel.deleteSuccess()){
            viewModel.updateViewModel(false, true);
        }
    }
}
