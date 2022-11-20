package LogOut.interface_adapters;

public class LogOutPresenter {

    private final boolean logOutSuccess;

    public LogOutPresenter(boolean success){
        this.logOutSuccess = success;
    }

    public boolean isLogOutSuccess(){
        return this.logOutSuccess;
    }
}
