package log_out.interface_adapters;

// data to be processed
public class LogOutControllerData {
    private final String logOutRequest;

    public LogOutControllerData(String logOutRequest) {
        this.logOutRequest = logOutRequest;
    }

    public String getLogOutRequest(){
        return this.logOutRequest;
    }
}
