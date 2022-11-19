package login.interface_adapters;

import login.use_case.*;

public class LogInViewModel  {

    private  boolean logInSuccess;
    private  String logInError;


    public LogInViewModel(Boolean logInSuccess, String logInError){
        this.logInSuccess = logInSuccess;
        this.logInError = logInError;
    }

    public void updateViewModel(LogInResponseModel responseModel){
            logInSuccess = responseModel.getLogInSuccess();
            logInError = responseModel.getErrorMessage();
    }

    public boolean getLogInSuccess(){
        return logInSuccess;
    }

    public String getLogInError(){
        return logInError;
    }


}


