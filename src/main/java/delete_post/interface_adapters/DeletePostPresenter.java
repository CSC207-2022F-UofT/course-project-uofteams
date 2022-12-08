package delete_post.interface_adapters;

import delete_post.use_case.DeletePostOutputBoundary;
import delete_post.use_case.DeletePostResponseModel;

public class DeletePostPresenter implements DeletePostOutputBoundary{

    private final DeletePostViewModel viewModel;

    /**
     * Initialize DeletePostPresenter object
     * @param viewModel Instance of DeletePostViewModel
     */
    public DeletePostPresenter(DeletePostViewModel viewModel){
        this.viewModel = viewModel;
    }

    @Override
    /**
     * Update viewModel to reflect deletion or failure
     */
    public void updateViewModel(DeletePostResponseModel responseModel){
        if (responseModel.deleteSuccess()){
            if (!responseModel.getIsTimer()){
                viewModel.updateViewModel(true, responseModel.getMessage());
            }
        }
        else if (!responseModel.deleteSuccess()){
            viewModel.updateViewModel(false, responseModel.getMessage());
        }
    }
}