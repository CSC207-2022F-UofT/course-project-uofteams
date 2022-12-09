package log_in.use_case;

/**
 * Data structure for inputted data to be passed into the LogInInteractor
 */
public class LogInRequestModel {

    // inputted email for log in
    private final String email;

    // inputted password for log in
    private final String password;

    /**
     *
     * @param email String of the email inputted
     * @param password String of the password inputted
     */
    public LogInRequestModel(String email, String password){
        this.email = email;
        this.password = password;
    }

    /**
     * Return inputted email
     * @return email to be returned
     */
    public String getEmail(){
        return this.email;
    }

    /**
     * return inputted password
     * @return password to be returned
     */
    public String getPassword(){
        return this.password;
    }
}
