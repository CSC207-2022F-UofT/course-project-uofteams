package make_post.use_case;

public class MakePostResponseModel {
    private boolean creationSuccess;
    private String errorMessage;

    /**
     * initialises the response model, which contains the output data for this use case.
     * @param creationSuccess a boolean that says whether the post creation was a success.
     * @param errorMessage an error message that describes what went wrong.
     */
    public MakePostResponseModel(boolean creationSuccess, String errorMessage){
        this.creationSuccess = creationSuccess;
        this.errorMessage = errorMessage;
    }
    public boolean isCreationSuccess(){
        return creationSuccess;
    }
    public String getErrorMessage(){
        return this.errorMessage;
    }
}
