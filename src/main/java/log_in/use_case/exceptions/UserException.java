package log_in.use_case.exceptions;

/**
 * Exception if LogIn was not successful
 */
public class UserException extends Exception{
    private final String error;

    /**
     * Intialize a UserException with a given error message
     * @param error the specific error which resulted in a failed LogIn Attempt
     */
    public UserException(String error){
        this.error = error;
    }


    /**
     * Return the error message
     * @return the error message to be returned
     */
    @Override
    public String getMessage(){
        return error;
    }
}
