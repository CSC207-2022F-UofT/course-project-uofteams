package log_in.use_case;

/**
 * Public interface for the input of the Log In use case
 */
public interface LogInInputBoundary {
    /**
     * Interface which tries to log in based on a users inputted data
     * @param logIn a request from a user to log in containing an inputted email/password
     */
    void logIn(LogInRequestModel logIn);
}

