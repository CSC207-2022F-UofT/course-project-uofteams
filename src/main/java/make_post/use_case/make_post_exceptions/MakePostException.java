package make_post.use_case.make_post_exceptions;

public class MakePostException extends Exception{
    private final String message;

    public MakePostException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
