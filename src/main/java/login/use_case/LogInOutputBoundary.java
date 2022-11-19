package login.use_case;

/**
 * This Interface contains the result of the log in
 */
public interface LogInOutputBoundary {
    void present(LogInResponseModel responseModel);
}
