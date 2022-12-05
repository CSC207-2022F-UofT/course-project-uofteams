package view_comment.use_case;

public interface ViewCommentOutputBoundary {
    /*
     * Interface which takes results of viewing comments
     *
     * */
    void present(ViewCommentResponseModel responseModel);
}
