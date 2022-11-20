package LogOut.interface_adapters;

// indicating that the logOut was successful and creating data to present
public class LogOutPresenterData {

    private final boolean success;

    public LogOutPresenterData(boolean success){
        this.success = success;
    }

    public boolean isSuccess(){
        return this.success;
    }
}
