package log_out.use_case;

import entities.CurrentUser;

public class LogOutInteractor implements LogOutInputBoundary {

    private final LogOutOutputBoundary outputBoundary;

    /**
     * Initialize a LogOutInteractor
     * @param outputBoundary contains the result for the response model
     */
    public LogOutInteractor(LogOutOutputBoundary outputBoundary){
        this.outputBoundary = outputBoundary;
    }

    /**
     * sets the current user to null to indicate a user logging out
     * @param requestModel this requestModel indicates that a log_out request was made
     * @return A response model is returned for the output boundary to present
     */
    private LogOutResponseModel logOutUser(LogOutRequestModel requestModel){
        CurrentUser.setCurrentUser(null);
        return new LogOutResponseModel(true);
    }

    /**
     * This use case is called to log out the user and create a response model to present
     * @param requestModel an indicator that a logout request has been made
     */
    @Override
    public void logOut(LogOutRequestModel requestModel) {
        LogOutResponseModel responseModel = this.logOutUser(requestModel);
        this.outputBoundary.present(responseModel);
    }
}
