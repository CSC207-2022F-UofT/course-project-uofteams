package log_in.interface_adapters;

/**
 * inputted data from the user to be processed
 */
public class LogInUserInputData {
    private final String emailInput;
    private final String passInput;

    /**
     * Initializes an instance of the input data
     * @param emailInput users email input
     * @param passInput users password input
     */
    public LogInUserInputData(String emailInput, String passInput){
        this.emailInput = emailInput;
        this.passInput = passInput;
    }

    /**
     * return users email
     * @return email to be returned
     */
    public String getEmail(){
        return this.emailInput;
    }

    /**
     * return user password
     * @return password to be returned
     */
    public String getPass(){
        return this.passInput;
    }
}
