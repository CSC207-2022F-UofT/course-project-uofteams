package make_comment.use_case;

public class MakeCommentResponseModel {
    // describes whether the attempt was success(successful if body is not empty)
    public boolean creationSuccess;

    // The error message. empty string if creationSuccess is True.
    public String errorMessage;

    /*
     * Initializes the SignUpResponseModel
     *
     * @param creationSuccess boolean representation of whether user was successfully created
     * @param message String rep of the error
     * */
    public MakeCommentResponseModel(boolean creation, String errorMessage) {
        this.creationSuccess = creation;
        this.errorMessage = errorMessage;
    }
}
