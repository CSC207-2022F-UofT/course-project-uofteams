package sign_up.interface_adapters;

// data passed in from user to
public class SignUpUserInputData {
    public String emailInput;
    public String passwordInput;
    public String adminInput;

    public SignUpUserInputData(String emailInput, String passwordInput, String adminInput) {
        this.emailInput = emailInput;
        this.passwordInput = passwordInput;
        this.adminInput = adminInput;
    }
}
