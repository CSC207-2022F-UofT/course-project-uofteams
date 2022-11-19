package login.interface_adapters;

import login.use_case.LogInInputBoundary;
import login.use_case.LogInInteractor;
import login.use_case.LogInRequestModel;

//takes user data and coverts in into information that can be passed to the Use Case
public class LogInController {

    private LogInInputBoundary interactor;

    private final String emailInput;
    private final String passwordInput;

    public LogInController(String emailInput, String passwordInput){
        this.emailInput = emailInput;
        this.passwordInput = passwordInput;

    }

    //Initializes the LogIn use case
    public void logInInitializer(LogInController controller) {
        LogInRequestModel requestModel = new LogInRequestModel(controller.getEmailInput(),
                controller.getPasswordInput());
        interactor.logIn(requestModel);

    }

    public String getEmailInput(){
        return this.emailInput;
    }

    public String getPasswordInput(){
        return this.passwordInput;
    }
}


