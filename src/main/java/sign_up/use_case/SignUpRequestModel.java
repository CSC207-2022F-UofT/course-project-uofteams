package sign_up.use_case;

public class SignUpRequestModel {
    // The inputted email for the account
    public String email;

    // The inputted password for the account
    public String password;

    // boolean on whether to check admin password
    public boolean checkAdmin;

    // The input attempt for admin password
    public String adminPassword;

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
}
