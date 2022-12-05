package make_post.interface_adapters;

import make_post.use_case.MakePostOutputBoundary;
import make_post.use_case.MakePostResponseModel;

public class MakePostPresenter implements MakePostOutputBoundary {
    private MakePostViewModel viewModel;

    /**
     * Initialises the presenter for this use case.
     * @param viewModel viewModel for this use case.
     */
    public MakePostPresenter(MakePostViewModel viewModel){
        this.viewModel = viewModel;
    }

    /**
     * updates the view model.
     * @param responseModel a data class that contains information whether the post creation was a success.
     */
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
