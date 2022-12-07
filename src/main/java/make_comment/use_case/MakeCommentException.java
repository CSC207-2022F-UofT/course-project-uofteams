package make_comment.use_case;

public class MakeCommentException extends Exception{
    private final String message;

    /**
     * An exception that occurs when make comment use case fails
     */
    public MakeCommentException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
