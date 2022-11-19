package login.interface_adapters;

import login.use_case.LogInInputBoundary;
import login.use_case.LogInInteractor;
import login.use_case.LogInRequestModel;

public class LogInController {
    private final LogInInputBoundary interactor;

    public LogInController(LogInInputBoundary interactor){
        this.interactor = interactor;
    }

    public void logInInitializer(String email, String password){
        LogInRequestModel requestModel = new LogInRequestModel(email, password);

        interactor.logIn(requestModel);
    }
}
