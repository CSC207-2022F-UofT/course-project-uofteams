package sign_up.use_case;

import entities.CurrentUser;
import entities.User;
import sign_up.use_case.exceptions.MakeUserException;

public class SignUpInteractor implements SignUpInputBoundary{
    // An object that accesses the database
    private final SignUpDataAccessInterface dataAccess;

    // An object that accesses the UI
    private final SignUpOutputBoundary outputBoundary;

    // An object whose job is to create User elements
    private final UserFactory userFactory;

    public SignUpInteractor(SignUpDataAccessInterface dataAccess, SignUpOutputBoundary outputBoundary){
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
        this.userFactory = new UserFactory();
    }

    @Override
    public void signUp(SignUpRequestModel requestModel) {
        try {
            SignUpResponseModel responseModel = this.signUpHelper(requestModel);
            this.outputBoundary.present(responseModel);
        }
        catch (MakeUserException e) {
            SignUpResponseModel responseModel = new SignUpResponseModel(false, e.getMessage());
        }


    }

    private SignUpResponseModel signUpHelper(SignUpRequestModel requestModel) throws MakeUserException {
        if (requestModel.email.isEmpty() || requestModel.password.isEmpty()) {
            throw new MakeUserException("empty field");
        }
        if (requestModel.email.endsWith("@mail.utoronto.ca")) {
            throw new MakeUserException("incorrect email");
        }
        if (!this.checkEmailExists(requestModel.email)) {
            throw new MakeUserException("email exists");
        }
        if (requestModel.checkAdmin && !this.checkAdminPassword(requestModel.adminPassword)) {
            throw new MakeUserException("admin password");
        }

        int numUsers = this.dataAccess.getNumberUsers();
        User newUser = this.createUser(requestModel.checkAdmin, numUsers, requestModel.email, requestModel.password);
        this.saveUser(newUser);
        this.updateCurrentUser(newUser);

        return new SignUpResponseModel(true, "");
    }

    private boolean checkEmailExists(String email){
        return dataAccess.checkUserEmailExists(email);
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
        this.dataAccess.saveUser(toSave, "user");
        this.dataAccess.setNumberUsers(User.numUsers);
    }


}
