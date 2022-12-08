package sign_up.interface_adapters;

import sign_up.use_case.SignUpOutputBoundary;
import sign_up.use_case.SignUpResponseModel;

/*
* The presenter which is called by the use case and stores the view model
* */
public class SignUpPresenter implements SignUpOutputBoundary {
    private final SignUpViewModel viewModel;

    /*
    * Initialize an instance of SignUpPresenter
    *
    * @param viewModel The view model which the presenter will store
    * */
    public SignUpPresenter(SignUpViewModel viewModel) {
        this.viewModel = viewModel;
    }

    /*
    *
    * */
    @Override
    public void updateViewModel(SignUpResponseModel responseModel) {
        if (responseModel.isCreationSuccess()) {
            viewModel.updateViewModel(new SignUpUserOutputData(responseModel.isCreationSuccess(),
                    responseModel.getMessage()));
        } else {
            viewModel.updateViewModel(new SignUpUserOutputData(responseModel.isCreationSuccess(),
                    responseModel.getMessage()));
        }
    }
}
