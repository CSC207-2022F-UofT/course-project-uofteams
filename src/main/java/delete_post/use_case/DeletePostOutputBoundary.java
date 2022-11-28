package delete_post.use_case;

public interface DeletePostOutputBoundary {
    void prepareFailView();
    void prepareSuccessView(DeletePostResponseModel responseModel);
    void prepareTimerView(DeletePostResponseModel responseModel);
}
