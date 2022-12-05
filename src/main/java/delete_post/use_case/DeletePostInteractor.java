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
            DeletePostResponseModel responseModel = deleteDatabase(requestModel, post);
            this.outputBoundary.prepareTimerView(responseModel);
        }
        else if (CurrentUser.getIsAdmin() ||
                CurrentUser.getCurrentUser() == post.getUser()){
            DeletePostResponseModel responseModel = deleteDatabase(requestModel, post);
            this.outputBoundary.prepareSuccessView(responseModel);
            }
        else{
            this.outputBoundary.prepareFailView();
        }
    }
    private DeletePostResponseModel deleteDatabase(DeletePostRequestModel requestModel, Post post){
        DeletePostResponseModel responseModel = new DeletePostResponseModel(requestModel.getPostId());

        for (int favUser : post.getFavouritedUsers()){
            this.dataAccess.removeFavourite(requestModel.getPostId(), favUser);
        }
        this.dataAccess.removeUser(requestModel.getPostId(), post.getUser());
        this.dataAccess.deletePost(requestModel.getPostId());
        return responseModel;
    }
}
