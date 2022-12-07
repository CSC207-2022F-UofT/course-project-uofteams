package view_post.use_case.view_post_exceptions;

public class PostDoesNotExistException extends Exception{
    private final String message;

    public PostDoesNotExistException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}