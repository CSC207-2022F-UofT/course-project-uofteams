package view_comment.use_case;

import java.util.ArrayList;

public class ViewCommentResponseModel {
    public final boolean hasComment;
    public final ArrayList<String[]> outputComments;
    public final String message;


    /**
     * Initializes the ViewCommentResponseModel
     *
     * @param message String rep of the error(when give post has no comments)
     * @param outputComments Array of comments of given post
     */
    public ViewCommentResponseModel(ArrayList<String[]> outputComments, String message) {
        this.hasComment = outputComments != null;
        this.outputComments = outputComments;
        this.message = message;
    }
    public boolean isReplies(){
        return hasComment;
    }
    public String getErrorMessage(){
        return this.message;
    }
    public ArrayList<String[]> getOutputComments(){return outputComments;}
}