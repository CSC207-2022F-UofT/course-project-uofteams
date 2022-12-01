package make_comment.use_case;

public class MakeCommentExceptions extends Exception{
    private final String message;

    public MakeCommentExceptions(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
