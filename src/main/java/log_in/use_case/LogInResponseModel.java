package log_in.use_case;

/**
 * Data for exiting the Log In use case interactor
 */
public class LogInResponseModel {

    // if log in was successful
    private final boolean LogInSuccess;

    // error message if not successful
    private final String errorMessage;

    /**
     *
     * @param logIn if log_in was successful or not
     * @param errorMessage the error message if failed log in
     */
    public LogInResponseModel(boolean logIn, String errorMessage){
        this.LogInSuccess = logIn;
        this.errorMessage = errorMessage;
    }

    /**
     * Return whether the LogIn was a success or not
     * @return boolean representation of LogIn attempt
     */
    public boolean getLogInSuccess(){
        return this.LogInSuccess;
    }

    /**
     * If unsuccessful LogIn, return what error caused it
     * @return the error message to be returned
     */
    public String getErrorMessage(){
        return this.errorMessage;
    }
}
