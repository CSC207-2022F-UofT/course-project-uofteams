package log_out.use_case;

/**
 * public interface carrying data indicating a LogOut request has been made
 */
public interface LogOutInputBoundary {

    /**
     * Interface which contains a logout request
     * @param requestModel a request to logout made by the user
     */
    void logOut(LogOutRequestModel requestModel);
}
