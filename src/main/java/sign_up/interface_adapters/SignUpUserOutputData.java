package sign_up.interface_adapters;

public class SignUpUserOutputData {
    public boolean creationSuccess;
    public String errorMessage;

    // Instance invariant: !success || errorMessage.isEmpty()

    public SignUpUserOutputData(boolean success, String errorMessage) {
        this.creationSuccess = success;
        this.errorMessage = errorMessage;
    }
}
