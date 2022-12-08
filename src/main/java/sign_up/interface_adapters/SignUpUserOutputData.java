package sign_up.interface_adapters;

/**
 * The outputted response from the use case interactor
 */
public class SignUpUserOutputData {
    private final boolean creationSuccess;
    private final String errorMessage;

    /**
     * Initialize a new SignUpUserOutputData with data from the use case interactor
     * @param success Whether a new account was successfully created
     * @param errorMessage If an account was not created, the reason why
     */
    public SignUpUserOutputData(boolean success, String errorMessage) {
        this.creationSuccess = success;
        this.errorMessage = errorMessage;
    }

    /**
     * Return whether an account was made
     * @return true if and only if an account was made
     */
    public boolean isCreationSuccess() { return creationSuccess; }

    /**
     * Return the error which caused an account not to be made, or the empty string if successful
     * @return A string containing the reason for account creation failure
     */
    public String getErrorMessage() {return errorMessage; }
}
