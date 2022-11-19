package makePost.use_case;

public class MakePostResponseModel {
    private boolean creationSuccess;
    private String errorMessage;

    public MakePostResponseModel(boolean creationSuccess, String errorMessage){
        this.creationSuccess = creationSuccess;
        this.errorMessage = errorMessage;
    }
}
