package log_in.interface_adapters;

import log_in.use_case.LogInOutputBoundary;
import log_in.use_case.LogInResponseModel;

/**
 * Presenter for the LogIn Use Case. Also updates the view through view model
 */
public class LogInPresenter implements LogInOutputBoundary {
    private final LogInViewModel viewModel;


    /**
     * Initialize a LogInPresenter
     * @param viewModel the LogInViewModel the presenter will interact with
     */
    public LogInPresenter(LogInViewModel viewModel){
        this.viewModel = viewModel;

    }


    /**
     * Update view model with whether the logIn was a success. If it wasn't an error message will be retrieved
     * @param responseModel LogInResponseModel bringing data from the interactor
     */
    @Override
    public void present(LogInResponseModel responseModel) {
        viewModel.updateViewModel(new LogInUserOutputData(responseModel.getLogInSuccess(),
                responseModel.getErrorMessage()));
    }
}
