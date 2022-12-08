package log_out.interface_adapters;

// data to be processed
public class LogOutUserInputData {
    private final String logOutRequest;

    /**
     * Intializes an instance of the input data
     * @param logOutRequest request model for the use case
     */
    public LogOutUserInputData(String logOutRequest) {
        this.logOutRequest = logOutRequest;
    }

    public String getLogOutRequest(){
        return this.logOutRequest;
    }
}
