package make_comment.use_case;

public class MakeCommentException extends Exception{
    private final String message;

    public MakeCommentException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
