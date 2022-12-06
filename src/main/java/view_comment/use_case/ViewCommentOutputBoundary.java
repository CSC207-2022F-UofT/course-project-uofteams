package view_comment.use_case;

public interface ViewCommentOutputBoundary {
    /**
     * Interface which takes results of viewing comments
     * @param responseModel a data structure containing output data
     */
    void present(ViewCommentResponseModel responseModel);
}
