package log_out.interface_adapters;

/**
 * output response from interactor
 */
public class LogOutUserOutputData {

    private final boolean success;

    /**
     * Initialize a LogOutUserOutputData with data from interactor
     * @param success boolean representation on a logouts success
     */
    public LogOutUserOutputData(boolean success){
        this.success = success;
    }

    public boolean isSuccess(){
        return this.success;
    }
}
