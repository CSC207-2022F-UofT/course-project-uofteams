package make_post.use_case.make_post_exceptions;

public class MakePostException extends Exception{
    private final String message;

    /**
     * An exception that occurs if the make post use case fails.
     * @param message the message to be related to the user upon failure.
     */
    public MakePostException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
