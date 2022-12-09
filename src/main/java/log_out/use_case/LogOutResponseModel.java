package log_out.use_case;

/**
 * Data that will be formatted to exit the interactor
 */
public class LogOutResponseModel {


    private final boolean success;

    /**
     *
     * @param success Parameter indicating the logout request was successful
     */
    public LogOutResponseModel(boolean success){
        this.success = success;
    }

    /**
     * whether the LogOut was successful
     * @return boolean representation of whether the LogOut was successful
     */
    public boolean isSuccess(){
        return this.success;
    }
}
