package log_in.interface_adapters;

import log_in.use_case.LogInInputBoundary;
import log_in.use_case.LogInRequestModel;

/**
 * takes user data and coverts in into information that can be passed to the Use Case
 */
public class LogInController {

    private final LogInInputBoundary interactor;


    /**
     * Initializes a LogInController instance
     * @param interactor input boundary for the use case
     */
    public LogInController(LogInInputBoundary interactor){
        this.interactor = interactor;

    }

    /**
     * converts the data into a requestModel which will be passed through the input boundary to the use case interactor
     * @param data data inputted from the user
     */
    public void logInInitializer(LogInUserInputData data) {
        LogInRequestModel requestModel = new LogInRequestModel(data.getEmail(), data.getPass());
        interactor.logIn(requestModel);
    }
}


