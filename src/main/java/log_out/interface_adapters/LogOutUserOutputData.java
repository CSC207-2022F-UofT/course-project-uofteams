package log_out.interface_adapters;

// indicating that the logOut was successful and creating data to present
public class LogOutUserOutputData {

    private final boolean success;

    public LogOutUserOutputData(boolean success){
        this.success = success;
    }

    public boolean isSuccess(){
        return this.success;
    }
}
