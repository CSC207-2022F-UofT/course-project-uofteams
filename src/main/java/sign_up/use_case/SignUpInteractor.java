package sign_up.use_case;

import entities.CurrentUser;
import entities.User;
import sign_up.use_case.exceptions.MakeUserException;

import java.util.List;

public class SignUpInteractor implements SignUpInputBoundary{
    // An object that accesses the database
    private final SignUpDsGateway dataAccess;

    // An object that accesses the UI
    private SignUpOutputBoundary outputBoundary;

    // An object whose job is to create User elements
    private final UserFactory userFactory;

    /*
    * Initialize a new instance of Sign Up Interactor
    *
    * @ param dataAccess A class implementing SignUpDSGateway with database access
    * */
    public SignUpInteractor(SignUpDsGateway dataAccess, SignUpOutputBoundary outputBoundary){
        this.dataAccess = dataAccess;
        this.userFactory = new UserFactory();
        this.outputBoundary = outputBoundary;
    }

    /*
    * The use case method for Sign up Interactor
    *
    * @param requestModel The data interactor requires to perform the use case
    * */
    @Override
    public void signUp(SignUpRequestModel requestModel) {
        try {
            SignUpResponseModel responseModel = this.signUpHelper(requestModel);
            this.outputBoundary.updateViewModel(responseModel);
        }
        catch (MakeUserException e) {
            SignUpResponseModel responseModel = new SignUpResponseModel(false, e.getMessage());
            this.outputBoundary.updateViewModel(responseModel);
        }


    }

    private SignUpResponseModel signUpHelper(SignUpRequestModel requestModel) throws MakeUserException {
        if (requestModel.getEmail().isEmpty() || requestModel.getPassword().isEmpty()) {
            throw new MakeUserException("empty field");
        }
        if (!requestModel.getEmail().endsWith("@mail.utoronto.ca")) {
            throw new MakeUserException("incorrect email");
        }
        if (this.checkEmailExists(requestModel.getEmail())) {
            throw new MakeUserException("email exists");
        }
        if (requestModel.isCheckAdmin() && !this.checkAdminPassword(requestModel.getAdminPassword())) {
            throw new MakeUserException("admin password");
        }

        int numUsers = this.dataAccess.getNumberUsers();
        User newUser = this.createUser(requestModel.isCheckAdmin(), numUsers, requestModel.getEmail(), requestModel.getPassword());
        this.saveUser(newUser);
        this.updateCurrentUser(newUser);
        this.setNumberUsers(numUsers);

        return new SignUpResponseModel(true, "");
    }

    private boolean checkEmailExists(String toCheck){
        List<String> emails = dataAccess.getEmails();
        for (String email: emails) {
            if (email.equals(toCheck)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkAdminPassword(String adminPassword){
        return dataAccess.getAdminPassword().equals(adminPassword);
    }

    private void updateCurrentUser(User toUpdate){
        CurrentUser.setCurrentUser(toUpdate);
    }

    private User createUser(boolean isAdmin, int numUsersCreated, String email, String password){
        return userFactory.create(isAdmin, numUsersCreated, email, password);
    }

    private void saveUser(User toSave){
        this.dataAccess.saveUser(toSave);
    }

    private void setNumberUsers(int numberUsers) {
        this.dataAccess.setNumberUsers(numberUsers);
    }


}
