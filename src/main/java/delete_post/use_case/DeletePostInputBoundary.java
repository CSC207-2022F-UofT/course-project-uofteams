package delete_post.use_case;

public interface DeletePostInputBoundary {
    /**
     * Delete Post data and all references to it in database
     * @param requestModel DeletePostRequestModel instance containing data necessary for deletion
     */
    void delete(DeletePostRequestModel requestModel);
}