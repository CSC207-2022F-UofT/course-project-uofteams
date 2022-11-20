package LogOut.interface_adapters;

import login.interface_adapters.LogInPresenter;

public class LogOutPresenter {

    private final boolean logOutSuccess;

    public LogOutPresenter(boolean success){
        this.logOutSuccess = success;
    }

    public boolean isLogOutSuccess(){
        return this.logOutSuccess;
    }
}
