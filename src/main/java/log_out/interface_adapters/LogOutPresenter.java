package log_out.interface_adapters;

import log_out.use_case.LogOutOutputBoundary;
import log_out.use_case.LogOutResponseModel;

public class LogOutPresenter implements LogOutOutputBoundary {

    private final LogOutViewModel viewModel;

    public LogOutPresenter(LogOutViewModel viewModel){
        this.viewModel = viewModel;
    }


    @Override
    public void present(LogOutResponseModel responseModel) {
        viewModel.updateViewModel(new LogOutUserOutputData(responseModel.isSuccess()));
    }
}
