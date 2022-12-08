package log_in.interface_adapters;

import log_in.use_case.LogInOutputBoundary;
import log_in.use_case.LogInResponseModel;

/**
 * The presenter for the log in use case, updates the view model
 */
public class LogInPresenter implements LogInOutputBoundary {
    private final LogInViewModel viewModel;

    /**
     * Initialize an instance of a LogInPresenter
     * @param viewModel the  LogInViewModel which the presenter will interact with
     */
    public LogInPresenter(LogInViewModel viewModel){
        this.viewModel = viewModel;

    }

    /**
     * update view model with a success or a fail which is given by the responseModel
     * @param responseModel the LogInResponseModel which brings data from the interactor
     */
    @Override
    public void present(LogInResponseModel responseModel) {
        viewModel.updateViewModel(new LogInUserOutputData(responseModel.getLogInSuccess(),
                responseModel.getErrorMessage()));
    }
}
