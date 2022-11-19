package sign_up.interface_adapters;

// data passed in from user to
public class SignUpUserInputData {
    private final String emailInput;
    private final String passwordInput;
    private final String adminInput;

    public SignUpUserInputData(String emailInput, String passwordInput, String adminInput) {
        this.emailInput = emailInput;
        this.passwordInput = passwordInput;
        this.adminInput = adminInput;
    }

    public String getEmailInput() {return this.emailInput; }

    public String getPasswordInput() {return this.passwordInput; }

    public String getAdminInput() {return this.adminInput; }
}
