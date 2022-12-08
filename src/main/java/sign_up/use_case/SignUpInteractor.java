package sign_up.use_case;

import entities.CurrentUser;
import entities.User;
import sign_up.use_case.exceptions.MakeUserException;

import java.util.ArrayList;
import java.util.List;

/**
 * The SignUpInteractor for the sign up use case. Responsible for applying use case logic to the entities
 */
public class SignUpInteractor implements SignUpInputBoundary{
    // An object that accesses the database
    private final SignUpDsGateway dataAccess;

    // An object that accesses the UI
    private final SignUpOutputBoundary outputBoundary;

    // An object whose job is to create User elements
    private final use_case_general.UserFactory userFactory;

    /**
    * Initialize a new instance of Sign Up Interactor
    *
    * @ param dataAccess A class implementing SignUpDSGateway with database access
    * */
    public SignUpInteractor(SignUpDsGateway dataAccess, SignUpOutputBoundary outputBoundary, use_case_general.UserFactory userFactory){
        this.dataAccess = dataAccess;
        this.userFactory = userFactory;
        this.outputBoundary = outputBoundary;
    }

    /**
     * Check to see if the input email exists, if the email is valid, if neither field is empty,
     * and if the admin password is valid. If all checks pass, create a new user and sign them in
     * @param requestModel The SignUpRequestModel with the user input data
     */
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

    /**
     * Performs the logic checks to see if input email exists, if the email is valid, if neither
     * field is empty, and if the admin password is valid. If all pass, creates a new user.
     * @param requestModel SignUpRequestModel containing user input data
     * @return a SignUpResponseModel with information about success of use case
     * @throws MakeUserException If any of the logic checks fail
     */
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

    /**
     * Check all the user emails to see if the entered one exists
     * @param toCheck String representation of email to check against saved email Strings
     * @return boolean representation of whether email exists in the system
     */
    private boolean checkEmailExists(String toCheck){
        List<String> emails = dataAccess.getEmails();
        for (String email: emails) {
            if (email.equals(toCheck)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check to see if the entered admin password is correct
     * @param adminPassword The attempted admin password
     * @return Whether the entered admin password is the same as the system admin password
     */
    private boolean checkAdminPassword(String adminPassword){
        return dataAccess.getAdminPassword().equals(adminPassword);
    }

    /**
     * Set the CurrentUser to the given User
     * @param toUpdate the User to update the CurrentUser with
     */
    private void updateCurrentUser(User toUpdate){
        CurrentUser.setCurrentUser(toUpdate);
    }

    /**
     * Create and return a new User
     * @param isAdmin Whether the user is to have admin permissions
     * @param numUsersCreated The number of users created, to set id
     * @param email The user's email
     * @param password The user's password
     * @return A User created with the information entered
     */
    private User createUser(boolean isAdmin, int numUsersCreated, String email, String password){
        return userFactory.create(isAdmin, numUsersCreated, email, password);
    }

    /**
     * Save the given User to the database
     * @param toSave the User to be saved
     */
    private void saveUser(User toSave){
        String[] userData = new String[6];
        userData[0] = String.valueOf(toSave.getId());
        userData[1] = Boolean.toString(toSave.isAdmin());
        userData[2] = toSave.getEmail();
        userData[3] = toSave.getPassword();
        userData[4] = convertListToString((ArrayList<Integer>) toSave.getPosts());
        userData[5] = convertListToString((ArrayList<Integer>) toSave.getFavourites());
        this.dataAccess.saveUser(userData);
    }

    /**
     * Update the number of Users in the database
     * @param numberUsers The number of Users to update to
     */
    private void setNumberUsers(int numberUsers) {
        this.dataAccess.setNumberUsers(numberUsers);
    }

    /**
     * Converts a list of integers to a string representation, as required by the database
     * @param list A list to be converted
     * @return A string corresponding to the input list
     */
    private String convertListToString(ArrayList<Integer> list) {
        String toReturn = "";

        for (Integer integer: list) {
            if (list.indexOf(integer) == 0) {
                toReturn = integer.toString().concat(toReturn);
            } else {
                toReturn = integer.toString().concat(" ").concat(toReturn);
            }
        }
        return toReturn;
    }


}
