package sign_up.interface_adapters;

import sign_up.use_case.SignUpOutputBoundary;
import sign_up.use_case.SignUpResponseModel;

/**
* The Presenter for the sign-up use case. Informs the View Model on how to update the view
* */
public class SignUpPresenter implements SignUpOutputBoundary {
    private final SignUpViewModel viewModel;

    /**
     * Initialize an instance of SignUpPresenter
     * @param viewModel the SignUpViewModel that the presenter will be interacting with
     */
    public SignUpPresenter(SignUpViewModel viewModel) {
        this.viewModel = viewModel;
    }

    /**
     * Update the View Model with a success or failure message, depending on the given Response Model.
     * @param responseModel the SignUpResponseModel carrying data from the interactor
     */
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
