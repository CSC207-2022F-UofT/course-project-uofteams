package logIn.use_case;

public class LogInResponseModel {

    // if log in was successful
    private final boolean LogInSuccess;

    // error message if not successful
    private final String errorMessage;

    /**
     *
     * @param logIn if logIn was successful or not
     * @param errorMessage the error message if failed log in
     */
    public LogInResponseModel(boolean logIn, String errorMessage){
        this.LogInSuccess = logIn;
        this.errorMessage = errorMessage;
    }

    public boolean getLogInSuccess(){
        return this.LogInSuccess;
    }

    public String getErrorMessage(){
        return this.errorMessage;
    }
}
