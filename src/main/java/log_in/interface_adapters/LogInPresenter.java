package log_in.interface_adapters;

import log_in.use_case.LogInOutputBoundary;
import log_in.use_case.LogInResponseModel;

public class LogInPresenter implements LogInOutputBoundary {
    private final LogInViewModel viewModel;


    public LogInPresenter(LogInViewModel viewModel){
        this.viewModel = viewModel;

    }


    @Override
    public void present(LogInResponseModel responseModel) {
        viewModel.updateViewModel(new LogInUserOutputData(responseModel.getLogInSuccess(),
                responseModel.getErrorMessage()));
    }
}
