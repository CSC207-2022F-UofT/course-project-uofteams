package log_out.use_case;

public class LogOutRequestModel {

    private final String logOutRequest;


    /**
     * Initialize a log_out Request
     * @param logOutRequest A string to indicate a logout request has been made
     */
    public LogOutRequestModel(String logOutRequest){
        this.logOutRequest  = logOutRequest;
    }

    public String getLogOutRequest(){
        return this.logOutRequest;
    }
}
