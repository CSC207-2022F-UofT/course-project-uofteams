package sign_up.use_case.exceptions;

public class MakeUserException extends Exception {
    private final String message;

    public MakeUserException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
