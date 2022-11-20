package LogOut.interface_adapters;

import LogOut.use_case.LogOutOutputBoundary;
import LogOut.use_case.LogOutResponseModel;

public class LogOutPresenter implements LogOutOutputBoundary {

    private final LogOutViewModel viewModel;

    public LogOutPresenter(LogOutViewModel viewModel){
        this.viewModel = viewModel;
    }


    @Override
    public void present(LogOutResponseModel responseModel) {
        viewModel.updateViewModel(new LogOutPresenterData(responseModel.isSuccess()));
    }
}
