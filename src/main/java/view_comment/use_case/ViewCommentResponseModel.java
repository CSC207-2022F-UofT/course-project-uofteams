package view_comment.use_case;

public class ViewCommentResponseModel {
    public boolean hasComment;

    // The error message. empty string if given post have comments.
    public String errorMessage;

    /*
     * Initializes the ViewCommentResponseModel
     *
     * @param hasComment boolean representation of whether Current post has Replies
     * @param message String rep of the error
     * */
    public ViewCommentResponseModel(boolean hasComment, String errorMessage) {
        this.hasComment = hasComment;
        this.errorMessage = errorMessage;
    }
    public boolean isCreationSuccess(){
        return hasComment;
    }
    public String getErrorMessage(){
        return this.errorMessage;
    }
}
