package delete_post.use_case;

public interface DeletePostOutputBoundary {
    void prepareFailView(String error);
    void prepareSuccessView(DeletePostResponseModel responseModel);
}
