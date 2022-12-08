package log_in.interface_adapters;

// inputted data to be processed
public class LogInControllerData {
    private final String emailInput;
    private final String passInput;

    public LogInControllerData(String emailInput, String passInput){
        this.emailInput = emailInput;
        this.passInput = passInput;
    }

    public String getEmail(){
        return this.emailInput;
    }

    public String getPass(){
        return this.passInput;
    }
}
