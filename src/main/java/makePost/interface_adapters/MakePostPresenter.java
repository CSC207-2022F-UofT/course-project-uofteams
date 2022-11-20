package makePost.interface_adapters;

import makePost.use_case.MakePostOutputBoundary;
import makePost.use_case.MakePostResponseModel;

public class MakePostPresenter implements MakePostOutputBoundary {
    private final MakePostViewModel viewModel;

    public MakePostPresenter(MakePostViewModel viewModel){
        this.viewModel = viewModel;
    }
    @Override
    public void present(MakePostResponseModel responseModel) {


    }
}
