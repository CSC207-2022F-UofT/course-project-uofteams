package log_out.interface_adapters;

import log_out.use_case.LogOutOutputBoundary;
import log_out.use_case.LogOutResponseModel;

/**
 * Presenter for the LogOut use case. Also informs view model to update the view
 */
public class LogOutPresenter implements LogOutOutputBoundary {

    private final LogOutViewModel viewModel;

    /**
     * An instance of LogOutPresenter
     * @param viewModel the viewModel which the presenter will interact with
     */
    public LogOutPresenter(LogOutViewModel viewModel){
        this.viewModel = viewModel;
    }


    /**
     * Update view model
     * @param responseModel LogOutResponseModel carrying data from interactor
     */
    @Override
    public void present(LogOutResponseModel responseModel) {
        viewModel.updateViewModel(new LogOutUserOutputData(responseModel.isSuccess()));
    }
}
