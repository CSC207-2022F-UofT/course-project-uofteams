package make_post.interface_adapters;

import make_post.use_case.MakePostOutputBoundary;
import make_post.use_case.MakePostResponseModel;

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
