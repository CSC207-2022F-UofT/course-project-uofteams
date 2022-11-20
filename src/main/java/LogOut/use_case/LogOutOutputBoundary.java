package LogOut.use_case;

/**
 * this interface indicates the logout was successful
 */
public interface LogOutOutputBoundary {

    void present(LogOutResponseModel responseModel);
}
