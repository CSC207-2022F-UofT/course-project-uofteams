package delete_post.interface_adapters;

import delete_post.use_case.DeletePostInputBoundary;
import delete_post.use_case.DeletePostRequestModel;
import entities.Post;

public class DeletePostController {

    private final DeletePostInputBoundary inputBoundary;

    public DeletePostController(DeletePostInputBoundary inputBoundary) {

        this.inputBoundary = inputBoundary;
    }

    public void delete(Post post){
        DeletePostRequestModel requestModel = new DeletePostRequestModel(post);
        inputBoundary.delete(requestModel);
    }

    public void delete(Post post, boolean isTimer){
        DeletePostRequestModel requestModel = new DeletePostRequestModel(post, isTimer);
        inputBoundary.delete(requestModel);
    }
}