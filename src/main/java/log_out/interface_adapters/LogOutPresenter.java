package log_out.interface_adapters;

import log_out.use_case.LogOutOutputBoundary;
import log_out.use_case.LogOutResponseModel;

/**
 * Presenter for the LogOut use case, updates the viewModel
 */
public class LogOutPresenter implements LogOutOutputBoundary {

    private final LogOutViewModel viewModel;

    /**
     * Initialize an instance of LogOutPresenter
     * @param viewModel The LogOutViewModel which the presenter will interact with
     */
    public LogOutPresenter(LogOutViewModel viewModel){
        this.viewModel = viewModel;
    }

    /**
     * update view model with the logOut responseModel
     * @param responseModel the LogOutResponseModel which brings data from the interactor
     */
    @Override
    public void present(LogOutResponseModel responseModel) {
        viewModel.updateViewModel(new LogOutUserOutputData(responseModel.isSuccess()));
    }
}
