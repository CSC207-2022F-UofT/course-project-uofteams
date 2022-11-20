package logIn.interface_adapters;

//data to be presented
public class LogInPresenterData {
    private final boolean success;
    private final String errorMessage;


    public LogInPresenterData(boolean success, String errorMessage){
        this.success = success;
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess(){
        return this.success;
    }

    public String getErrorMessage(){
        return this.errorMessage;
    }
}
