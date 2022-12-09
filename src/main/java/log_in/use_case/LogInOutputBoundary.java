package log_in.use_case;

/**
 * This Interface contains the result of the log in
 */
public interface LogInOutputBoundary {

    /**
     * format output from the interactor and to update LogInViewModel
     * @param responseModel the responseModel containing results of an attempted Log In
     */
    void present(LogInResponseModel responseModel);
}
