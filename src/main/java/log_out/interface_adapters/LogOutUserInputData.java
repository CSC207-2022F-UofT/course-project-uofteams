package log_out.interface_adapters;

import log_out.use_case.LogOutRequestModel;

// data to be processed
public class LogOutUserInputData {
    private final LogOutRequestModel logOutRequest;

    /**
     * Intializes an instance of the input data
     * @param logOutRequest request model for the use case
     */
    public LogOutUserInputData(LogOutRequestModel logOutRequest) {
        this.logOutRequest = logOutRequest;
    }

    public LogOutRequestModel getLogOutRequest(){
        return this.logOutRequest;
    }
}
