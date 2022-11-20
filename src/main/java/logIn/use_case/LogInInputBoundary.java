package logIn.use_case;

public interface LogInInputBoundary {
    /**
     * Interface which tries to log in based on a users inputted data
     * @param logIn a request from a user to log in containing an inputted email/password
     */
    void logIn(LogInRequestModel logIn);
}

