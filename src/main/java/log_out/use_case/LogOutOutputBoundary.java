package log_out.use_case;

/**
 * this interface indicates the logout was successful
 */
public interface LogOutOutputBoundary {

    /**
     * format output from interactor into a responseModel
     * @param responseModel the responseModel carrying data from interactor indicating the LogOut was a success or not
     */
    void present(LogOutResponseModel responseModel);
}
