package log_in.use_case;

import entities.CurrentUser;
import entities.User;
import log_in.use_case.exceptions.UserException;

import java.util.ArrayList;
import java.util.List;

public class LogInInteractor implements LogInInputBoundary {
    private final LogInDsGateway access;


    private final LogInOutputBoundary outputBoundary;

    private final UserFactory userFactory;


    /**
     * Initialize a LogInInteractor
     *
     * @param access to retrieve stuff from the database
     * @param outputBoundary contains the result of this use case
     */
    public LogInInteractor(LogInDsGateway access, LogInOutputBoundary outputBoundary, UserFactory factory){
        this.access = access;
        this.outputBoundary = outputBoundary;
        this.userFactory = new UserFactory();
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
     * @param email email of user that logged in
     * @param pass password of user that logged in
     */
    private void setCurrentUser(boolean success, String email, String pass){
        // create a factory to create the user for clean architecture
        String[] userInfo;
        userInfo = access.getUser(success, email, pass);

        boolean isAdmin = userInfo[1].equals("True");

        List<Integer> userPosts = this.userFactory.convertPost(userInfo[4]);

        List<Integer> userFavs = this.userFactory.convertPost(userInfo[5]);

        User currentUser = this.userFactory.create(isAdmin, 0, userInfo[2],
                userInfo[3], userPosts, userFavs);
        CurrentUser.setCurrentUser(currentUser);
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
        if (this.checkPassword(requestModel.getEmail(), requestModel.getPassword())){
            setCurrentUser(true, requestModel.getEmail(), requestModel.getPassword());
        }
        return new LogInResponseModel(true, "");
    }


    /**
     * method to compile a log_in request from a user
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