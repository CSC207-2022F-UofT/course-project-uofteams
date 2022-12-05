package delete_post.use_case;

public class DeletePostResponseModel {

    private final boolean deleted;
    private final boolean isTimer;

    public DeletePostResponseModel(boolean deleted, boolean isTimer) {
        this.deleted = deleted;
        this.isTimer = isTimer;
    }

    public boolean deleteSuccess(){
        return this.deleted;
    }
    public boolean getIsTimer(){
        return this.isTimer;
    }
}
