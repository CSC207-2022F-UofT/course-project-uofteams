package make_comment.use_case;
/*
 * Interface which takes results of attempts to create a comment
 *
 * */
public interface MakeCommentOutputBoundary {
    /**
     * updates the view model
     * @param responseModel a class containing the output data.
     */
    void present(MakeCommentResponseModel responseModel);
}