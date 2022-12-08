package log_out.interface_adapters;

// indicating that the logOut was successful and creating data to present
public class LogOutUserOutputData {

    private final boolean success;

    /**
     * Initialize a new LogOutOutputData with data from the interactor
     * @param success if LogOut was successful or not
     */
    public LogOutUserOutputData(boolean success){
        this.success = success;
    }


    public boolean isSuccess(){
        return this.success;
    }
}
