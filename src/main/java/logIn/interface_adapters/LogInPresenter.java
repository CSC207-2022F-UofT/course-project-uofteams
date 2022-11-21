package logIn.interface_adapters;

import logIn.use_case.LogInOutputBoundary;
import logIn.use_case.LogInResponseModel;

public class LogInPresenter implements LogInOutputBoundary {
    private final LogInViewModel viewModel;


    public LogInPresenter(LogInViewModel viewModel){
        this.viewModel = viewModel;

    }


    @Override
    public void present(LogInResponseModel responseModel) {
        viewModel.updateViewModel(new LogInPresenterData(responseModel.getLogInSuccess(),
                responseModel.getErrorMessage()));
    }
}
