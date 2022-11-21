package makePost.interface_adapters;

import makePost.use_case.MakePostOutputBoundary;
import makePost.use_case.MakePostResponseModel;

public class MakePostPresenter implements MakePostOutputBoundary {
    private MakePostViewModel viewModel;

    public MakePostPresenter(MakePostViewModel viewModel){
        this.viewModel = viewModel;
    }

    @Override
    public void updateViewModel(MakePostResponseModel responseModel) {
        if (responseModel.isCreationSuccess()) {
            viewModel.updateViewModel(responseModel.isCreationSuccess(),
                    responseModel.getErrorMessage());
        } else {
            viewModel.updateViewModel(responseModel.isCreationSuccess(),
                    responseModel.getErrorMessage());
        }

    }
}
