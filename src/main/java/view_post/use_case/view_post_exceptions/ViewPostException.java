package view_post.use_case.view_post_exceptions;

public class ViewPostException extends Exception{
    private final String message;

    public ViewPostException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}