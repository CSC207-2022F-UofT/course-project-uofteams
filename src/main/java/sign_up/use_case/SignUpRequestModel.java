package sign_up.use_case;

public class SignUpRequestModel {
    // The inputted email for the account
    private final String email;

    // The inputted password for the account
    private final String password;

    // boolean on whether to check admin password
    private final boolean checkAdmin;

    // The input attempt for admin password
    private final String adminPassword;

    /*
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

    public String getEmail() {return this.email; }

    public String getPassword() {return this.password; }

    public boolean isCheckAdmin() {return this.checkAdmin; }

    public String getAdminPassword() {return this.adminPassword; }
}
