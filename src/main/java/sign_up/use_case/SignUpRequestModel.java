package sign_up.use_case;

/**
 * The data structure for entering data into the sign up use case interactor
 */
public class SignUpRequestModel {
    // The inputted email for the account
    private String email;

    // The inputted password for the account
    private String password;

    // boolean on whether to check admin password
    private boolean checkAdmin;

    // The input attempt for admin password
    private String adminPassword;

    /**
    * Initializes a SignUpResponseModel
    *
    * @param email String representation of the inputted email
    * @param password String representation of the inputted password
    * @param checkAdmin boolean rep of whether to check adminPassword
    * @param adminPassword String rep of admin password to check
    */
    public SignUpRequestModel(String email, String password, boolean checkAdmin, String adminPassword) {
        this.email = email;
        this.password = password;
        this.checkAdmin = checkAdmin;
        this.adminPassword = adminPassword;
    }

    /**
     * Return the inputted user email
     * @return the user's email
     */
    public String getEmail() {return this.email; }

    /**
     * Return the inputted user password
     * @return the user's password
     */
    public String getPassword() {return this.password; }

    /**
     * Return whether the user is creating an admin account
     * @return boolean for if an admin is being created
     */
    public boolean isCheckAdmin() {return this.checkAdmin; }

    /**
     * Return the inputted admin password attempt
     * @return the admin password attempt
     */
    public String getAdminPassword() {return this.adminPassword; }
}
