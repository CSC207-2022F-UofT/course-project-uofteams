package sign_up.use_case;

/**
 * Public Interface for the input the sign-up use case interactor
 */
public interface SignUpInputBoundary {
    /**
     * Check to see if the input email exists, if the email is valid, if neither field is empty,
     * and if the admin password is valid. If all checks pass, create a new user and sign them in
     * @param requestModel The SignUpRequestModel with the user input data
     */
    void signUp(SignUpRequestModel requestModel);
}
