package log_out.interface_adapters;

import log_out.use_case.LogOutInputBoundary;
import log_out.use_case.LogOutRequestModel;

/**
 * creates a usable request to pass to the use case when a User wants to log out
  */
public class LogOutController {

    private final LogOutInputBoundary interactor;

    /**
     * initialize a LogOutController Instance
     * @param inputBoundary input boundary for the use case
     */
    public LogOutController(LogOutInputBoundary inputBoundary){
        this.interactor = inputBoundary;
    }

    //Initializes the logOut Use case
    public void logOutInitializer(LogOutUserInputData dataFromController){
        LogOutRequestModel requestModel = dataFromController.getLogOutRequest();
        interactor.logOut(requestModel);
    }
}
