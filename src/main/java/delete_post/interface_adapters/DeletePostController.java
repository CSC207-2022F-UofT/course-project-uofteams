package delete_post.interface_adapters;

import delete_post.use_case.DeletePostInputBoundary;
import delete_post.use_case.DeletePostRequestModel;

public class DeletePostController {

    private final DeletePostInputBoundary inputBoundary;

    /**
     * Initialize DeletePostController object
     * @param inputBoundary Instance of InputBoundary
     */
    public DeletePostController(DeletePostInputBoundary inputBoundary) {

        this.inputBoundary = inputBoundary;
    }

    /**
     * Delete post and all reference to it from database
     * @param postId ID of post to be deleted
     * @param isTimer true if delete is called from the Timer use case
     */
    public void delete(int postId, boolean isTimer){
        DeletePostRequestModel requestModel = new DeletePostRequestModel(postId, isTimer);
        inputBoundary.delete(requestModel);
    }
}