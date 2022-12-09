package log_out.interface_adapters;

/**
 * data indicating a log out request has been made
 */
public class LogOutUserInputData {
    private final String logOutRequest;

    /**
     * Initialize a request to LogOut
     * @param logOutRequest String representation of a LogOut request
     */
    public LogOutUserInputData(String logOutRequest) {
        this.logOutRequest = logOutRequest;
    }

    public String getLogOutRequest(){
        return this.logOutRequest;
    }
}
