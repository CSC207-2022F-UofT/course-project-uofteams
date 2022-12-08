package sign_up.use_case;

/**
 * The data structure for data exiting the sign up use case interactor
 */
public class SignUpResponseModel {
    // Whether the attempt to create user was successful
   private final boolean creationSuccess;

    // The error message. empty string if creationSuccess is false
    private final String message;

    /**
    * Initializes the SignUpResponseModel
    *
    * @param creation boolean representation of whether user was successfully created
    * @param errorMessage String rep of the error that occurred
    */
    public SignUpResponseModel(boolean creation, String errorMessage) {
        this.creationSuccess = creation;
        this.message = errorMessage;
    }

    /**
     * Return whether the creation was successful
     * @return boolean representation of creation success
     */
    public boolean isCreationSuccess() { return creationSuccess; }

    /**
     * Return what caused an error, if the creation was unsuccessful
     * @return string representation of the error
     */
    public String getMessage() {return this.message; }
}
