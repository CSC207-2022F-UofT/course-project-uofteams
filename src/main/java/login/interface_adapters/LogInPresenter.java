package login.interface_adapters;

import login.use_case.LogInOutputBoundary;
import login.use_case.LogInResponseModel;

public class LogInPresenter implements LogInOutputBoundary {
    private final LogInViewModel viewModel;

    public LogInPresenter(LogInViewModel viewModel){
        this.viewModel = viewModel;
    }


    @Override
    public void present(LogInResponseModel responseModel) {
        if (responseModel.getLogInSuccess()){
            viewModel.updateViewModel(responseModel);
        } else {
            LogInResponseModel responseModelFail = new LogInResponseModel(responseModel.getLogInSuccess(),
                    responseModel.getErrorMessage());
            viewModel.updateViewModel(responseModelFail);
        }
    }
}
