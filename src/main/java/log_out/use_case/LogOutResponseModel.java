package log_out.use_case;

public class LogOutResponseModel {


    private final boolean success;

    /**
     *
     * @param success Parameter indicating the logout request was successful
     */
    public LogOutResponseModel(boolean success){
        this.success = success;
    }

    public boolean isSuccess(){
        return this.success;
    }
}
