package makePost.use_case;

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
