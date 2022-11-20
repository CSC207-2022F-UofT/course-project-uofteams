package logIn.use_case;

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

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }
}
