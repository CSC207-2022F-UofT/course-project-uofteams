package delete_post.use_case;

public interface DeletePostOutputBoundary {
    /**
     * update view model based on response model
     * @param responseModel DeletePostResponseModel instance
     */
    void updateViewModel(DeletePostResponseModel responseModel);
}
