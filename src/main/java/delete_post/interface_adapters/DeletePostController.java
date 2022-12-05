package delete_post.interface_adapters;

import delete_post.use_case.DeletePostInputBoundary;
import delete_post.use_case.DeletePostRequestModel;

public class DeletePostController {

    private final DeletePostInputBoundary inputBoundary;

    public DeletePostController(DeletePostInputBoundary inputBoundary) {

        this.inputBoundary = inputBoundary;
    }

    public void delete(int postId, boolean isTimer){
        DeletePostRequestModel requestModel = new DeletePostRequestModel(postId, isTimer);
        inputBoundary.delete(requestModel);
    }
}
