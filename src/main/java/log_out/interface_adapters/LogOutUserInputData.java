package log_out.interface_adapters;

// data to be processed
public class LogOutUserInputData {
    private final String logOutRequest;

    public LogOutUserInputData(String logOutRequest) {
        this.logOutRequest = logOutRequest;
    }

    public String getLogOutRequest(){
        return this.logOutRequest;
    }
}
