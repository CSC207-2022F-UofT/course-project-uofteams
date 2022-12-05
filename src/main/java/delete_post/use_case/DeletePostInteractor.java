package delete_post.use_case;

import entities.CurrentUser;
import entities.Post;

public class DeletePostInteractor implements DeletePostInputBoundary{

    private final DeletePostOutputBoundary outputBoundary;
    private final DeletePostDsGateway dataAccess;

    public DeletePostInteractor(DeletePostOutputBoundary outputBoundary, DeletePostDsGateway dataAccess){
        this.outputBoundary = outputBoundary;
        this.dataAccess = dataAccess;
    }

    @Override
    public void delete(DeletePostRequestModel requestModel){
        Post post = dataAccess.getPost(requestModel.getPostId());

        if (requestModel.getIsTimer()){
            //will fix
        }
        else if (CurrentUser.getIsAdmin() ||
                CurrentUser.getCurrentUser().getId() == post.getUser()){
            DeletePostResponseModel responseModel = deleteDatabase(requestModel, post, true);
            this.outputBoundary.updateViewModel(responseModel);
            }
        else {
            DeletePostResponseModel responseModel = deleteDatabase(requestModel, post, false);
            this.outputBoundary.updateViewModel(responseModel);
        }
    }
    private DeletePostResponseModel deleteDatabase(DeletePostRequestModel requestModel, Post post, boolean success){
        DeletePostResponseModel responseModel = new DeletePostResponseModel(success, requestModel.getIsTimer());
        if (success) {
            for (int favUser : post.getFavouritedUsers()) {
                this.dataAccess.removeFavourite(requestModel.getPostId(), favUser);
            }
            for (int comment : post.getReplies()){
                this.dataAccess.deleteComment(comment);
            }
            this.dataAccess.removeUser(requestModel.getPostId(), post.getUser());
            this.dataAccess.deletePost(requestModel.getPostId());
        }
        return responseModel;
    }
}
