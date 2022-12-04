package make_post.use_case;

public interface MakePostOutputBoundary {
    /**
     * updates the view model
     * @param responseModel a class containing the output data.
     */
    void updateViewModel(MakePostResponseModel responseModel);
}
