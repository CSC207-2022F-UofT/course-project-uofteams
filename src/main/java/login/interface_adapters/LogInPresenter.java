package login.interface_adapters;

import login.use_case.LogInOutputBoundary;
import login.use_case.LogInResponseModel;

public class LogInPresenter {

    private final boolean logInSuccess;
    private final String errorMessage;

    public LogInPresenter(boolean success, String errorMessage){
        this.logInSuccess = success;
        this.errorMessage = errorMessage;
    }

    public boolean isLogInSuccess(){
        return this.logInSuccess;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
