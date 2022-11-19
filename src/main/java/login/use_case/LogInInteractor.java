package login.use_case;

import entities.CurrentUser;
import entities.User;
import login.exceptions.UserException;

public class LogInInteractor implements LogInInputBoundary {
    private final LogInDsGateway access;


    private final LogInOutputBoundary outputBoundary;


    /**
     * Initialize a LogInInteractor
     *
     * @param access to retrieve stuff from the database
     * @param outputBoundary contains the result of this use case
     */
    public LogInInteractor(LogInDsGateway access, LogInOutputBoundary outputBoundary){
        this.access = access;
        this.outputBoundary = outputBoundary;
    }

    /**
     *
     * @param email inputted email to be checked
     * @return if this email matches any in the database
     */
    private boolean checkEmail(String email){
        return access.checkUserEmailExists(email);
    }

    /**
     *
     * @param email inputted email to be checked
     * @param pass inputted password to be checked
     * @return if the email and password match each other
     */
    private boolean checkPassword(String email, String pass){
        return access.checkPasswordMatches(email, pass);
    }

    /**
     *
     * @param userLoggedIn this will be a user who has successfully logged in
     * Current user will set this user as a current user
     */
    private void setCurrentUser(User userLoggedIn){
        CurrentUser.setCurrentUser(userLoggedIn);
    }

    /**
     *
     * @param requestModel this request model contains an email and password inputted from the user
     * @return will return the result of users log in
     * @throws UserException will specify the error to the user if there is incorrect information
     */

    private LogInResponseModel logInCheck(LogInRequestModel requestModel) throws UserException {
        if (requestModel.getEmail().isEmpty() || requestModel.getPassword().isEmpty()){
            throw new UserException("Empty Email or Password");
        }
        if (!this.checkEmail(requestModel.getEmail())) {
            throw new UserException("Incorrect Email");
        }
        if (!this.checkPassword(requestModel.getEmail(), requestModel.getPassword())){
            throw new UserException("Incorrect Password");
        }
        this.setCurrentUser(access.getUser());
        return new LogInResponseModel(true, "");
    }


    /**
     * method to compile a login request from a user
     * @param requestModel a request from a user to log in containing an inputted email/password
     *
     */
    @Override
    public void logIn(LogInRequestModel requestModel){
        try {
            LogInResponseModel responseModel = this.logInCheck(requestModel);
            this.outputBoundary.present(responseModel);
        }
        catch (UserException exception) {
            LogInResponseModel responseModel = new LogInResponseModel(false, exception.getMessage());
            this.outputBoundary.present(responseModel);
        }

    }

}
