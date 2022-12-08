package sign_up.use_case.exceptions;

/**
 * Exception that will be thrown if logic checks fail when creating user
 */
public class MakeUserException extends Exception {
    private final String message;

    /**
     * Initialize a MakeUserException with the given message
     * @param message The error that caused the logic check to fail
     */
    public MakeUserException(String message) {
        this.message = message;
    }

    /**
     * Return the error message
     * @return The String representation of the message
     */
    @Override
    public String getMessage() {
        return message;
    }
}
