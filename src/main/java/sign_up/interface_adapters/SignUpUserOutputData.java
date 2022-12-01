package sign_up.interface_adapters;

public class SignUpUserOutputData {
    private final boolean creationSuccess;
    private final String errorMessage;

    // Instance invariant: !success || errorMessage.isEmpty()

    public SignUpUserOutputData(boolean success, String errorMessage) {
        this.creationSuccess = success;
        this.errorMessage = errorMessage;
    }

    public boolean isCreationSuccess() { return creationSuccess; }

    public String getErrorMessage() {return errorMessage; }
}
