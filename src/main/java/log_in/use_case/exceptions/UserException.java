package log_in.use_case.exceptions;

/**
 * Exception that will be thrown if LogIn Checks fail
 */
public class UserException extends Exception{
    private final String error;

    /**
     * Initialize a UserException with a specific error message
     * @param error the error that cause the LogIn check to fail
     */
    public UserException(String error){
        this.error = error;
    }

    /**
     * Return the error message
     * @return the error message to be returned as a String
     */
    @Override
    public String getMessage(){
        return error;
    }
}
