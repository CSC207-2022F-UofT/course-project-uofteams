package delete_post.use_case;

public class DeletePostResponseModel {

    private final boolean deleted;
    private final boolean isTimer;
    private final String message;

    /**
     * Initialize DeletePostResponseModel object
     * @param deleted true if deletion was successful
     * @param isTimer true if deletion was requested by timer
     * @param message message to be displayed in pop up frame
     */
    public DeletePostResponseModel(boolean deleted, boolean isTimer, String message) {
        this.deleted = deleted;
        this.isTimer = isTimer;
        this.message = message;
    }

    /**
     * Check if deletion was successful
     * @return true if deletion was successful
     */
    public boolean deleteSuccess(){
        return this.deleted;
    }

    /**
     * Check if deletion was requested by timer
     * @return true if deletion was requested by timer
     */
    public boolean getIsTimer(){
        return this.isTimer;
    }

    /**
     * Get message to be displayed in pop up frame
     * @return message to be displayed
     */
    public String getMessage(){return this.message;}
}