package view_comment.use_case;

public class ViewCommentException extends Exception{
    private final String message;

    public ViewCommentException(String message) {
        this.message = message;

    }
    @Override
    public String getMessage() {
        return message;
    }
}