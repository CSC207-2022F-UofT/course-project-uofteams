package view_comment.use_case;

import java.util.ArrayList;

public class ViewCommentResponseModel {
    public final ArrayList<String[]> outputComments;
    public String errorMessage;
    public boolean hasComment;

    /*
     * Initializes the ViewCommentResponseModel
     *
     * @param hasComment boolean representation of whether Current post has Replies
     * @param message String rep of the error(when give post has no comments)
     * @param outputComments Array of comments of given post
     * */
    public ViewCommentResponseModel(ArrayList<String[]> outputComments, String errorMessage) {
        this.hasComment = outputComments.isEmpty();
        this.outputComments = outputComments;
        this.errorMessage = errorMessage;
    }
    public boolean isReplies(){
        return hasComment;
    }
    public String getErrorMessage(){
        return this.errorMessage;
    }
    public ArrayList<String[]> getOutputComments(){return outputComments;}
}
