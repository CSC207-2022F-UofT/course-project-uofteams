package make_comment.use_case;
/*
 * Interface which takes results of attempts to create a comment
 *
 * */
public interface MakeCommentOutputBoundary {
    void present(MakeCommentResponseModel responseModel);
}