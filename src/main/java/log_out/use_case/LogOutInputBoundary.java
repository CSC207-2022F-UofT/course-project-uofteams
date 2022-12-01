package log_out.use_case;

public interface LogOutInputBoundary {

    /**
     * Interface which contains a logout request
     * @param requestModel a request to logout made by the user
     */
    void logOut(LogOutRequestModel requestModel);
}
