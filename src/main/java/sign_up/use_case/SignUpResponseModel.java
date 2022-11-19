package sign_up.use_case;

public class SignUpResponseModel {
    // Whether the attempt to create user was successful
   private final boolean creationSuccess;

    // The error message. empty string if creationSuccess is false
    private final String message;

    /*
    * Initializes the SignUpResponseModel
    *
    * @param creationSuccess boolean representation of whether user was successfully created
    * @param message String rep of the error
    * */
    public SignUpResponseModel(boolean creation, String errorMessage) {
        this.creationSuccess = creation;
        this.message = errorMessage;
    }

    public boolean isCreationSuccess() { return creationSuccess; }

    public String getMessage() {return this.message; }
}
