package delete_post.use_case;

public class DeletePostResponseModel {

    private final boolean deleted;
    private final boolean isTimer;
    private final String message;

    public DeletePostResponseModel(boolean deleted, boolean isTimer, String message) {
        this.deleted = deleted;
        this.isTimer = isTimer;
        this.message = message;
    }

    public boolean deleteSuccess(){
        return this.deleted;
    }
    public boolean getIsTimer(){
        return this.isTimer;
    }
    public String getMessage(){return this.message;}
}