package delete_post.use_case;

public class DeletePostException extends Exception{
    private final String message;

    public DeletePostException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
