package sign_up.interface_adapters;

/**
 * The data inputted by the user
 */
public class SignUpUserInputData {
    private final String emailInput;
    private final String passwordInput;
    private final String adminInput;

    /**
     * Initialize an instantiation of the input data.
     * @param emailInput The user's email
     * @param passwordInput The user's password
     * @param adminInput The admin password
     */
    public SignUpUserInputData(String emailInput, String passwordInput, String adminInput) {
        this.emailInput = emailInput;
        this.passwordInput = passwordInput;
        this.adminInput = adminInput;
    }

    /**
     * Return the user's email
     * @return The user's inputted email
     */
    public String getEmailInput() {return this.emailInput; }

    /**
     * Return the users password
     * @return the user's inputted password
     */
    public String getPasswordInput() {return this.passwordInput; }

    /**
     * Return the user's input for the admin password, if applicable
     * @return the user's inputted attempt for the admin password
     */
    public String getAdminInput() {return this.adminInput; }
}
