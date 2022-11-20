package LogOut.interface_adapters;

import LogOut.use_case.LogOutInputBoundary;
import LogOut.use_case.LogOutRequestModel;

public class LogOutController {

    private LogOutInputBoundary interactor;
    private final String logOut;

    public LogOutController(String logOut){
        this.logOut = logOut;
    }

    public void LogOutInitializer(LogOutController controller){
        LogOutRequestModel requestModel = new LogOutRequestModel(this.logOut);
        interactor.logOut(requestModel);
    }
}
