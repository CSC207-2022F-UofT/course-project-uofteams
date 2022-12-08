package view_comment.use_case;

public class ViewCommentNoPostException extends Exception{
    private final String message;

    public ViewCommentNoPostException(String message) {
        this.message = message;

    }
    @Override
    public String getMessage() {
        return message;
    }
}
