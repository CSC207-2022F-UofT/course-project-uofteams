package log_in.interface_adapters;

/**
 * Output from the interactor use case
 */
public class LogInUserOutputData {
    private final boolean success;
    private final String errorMessage;


    /**
     * Initialize a new LogInOutputData with data from the interactor
     * @param success if the logIn was successful or not
     * @param errorMessage the error message if the logIn was not successful
     */
    public LogInUserOutputData(boolean success, String errorMessage){
        this.success = success;
        this.errorMessage = errorMessage;
    }

    /**
     * If the logIn was a success or not
     * @return true if logIn was a success
     */
    public boolean isSuccess(){
        return this.success;
    }

    /**
     * return an error which caused the logIn to not be successful
     * @return the error message
     */
    public String getErrorMessage(){
        return this.errorMessage;
    }
}
