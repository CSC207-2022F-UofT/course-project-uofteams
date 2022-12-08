package delete_post.use_case;

import entities.CurrentUser;
import entities.Post;

import static java.util.Objects.isNull;

public class DeletePostInteractor implements DeletePostInputBoundary{

    private final DeletePostOutputBoundary outputBoundary;
    private final DeletePostDsGateway dataAccess;

    /**
     * Initialize instance of DeletePostInteractor object
     * @param outputBoundary DeletePostOutputBoundary object
     * @param dataAccess DeletePostDsGateway object
     */
    public DeletePostInteractor(DeletePostOutputBoundary outputBoundary, DeletePostDsGateway dataAccess){
        this.outputBoundary = outputBoundary;
        this.dataAccess = dataAccess;
    }

    /**
     * Update view model based on if deletion was successful
     * @param requestModel DeletePostRequestModel instance containing data necessary for deletion
     */
    @Override
    public void delete(DeletePostRequestModel requestModel){
        Post post = dataAccess.getPostDelete(requestModel.getPostId());
        DeletePostResponseModel responseModel;

        if (isNull(post)){
            responseModel = deleteDatabase(requestModel, null, false, "null");
        }
        else if (CurrentUser.getIsAdmin() ||
                CurrentUser.getCurrentUser().getId() == post.getUser()){
            responseModel = deleteDatabase(requestModel, post, true, "");
            }
        else {
            responseModel = deleteDatabase(requestModel, post, false, "permission");
        }
        this.outputBoundary.updateViewModel(responseModel);
    }

    /**
     * Helper method to delete post data and all references to it in database
     * @param requestModel request model containing information of post to be deleted
     * @param post Post object to be deleted
     * @param success true if deletion is successful
     * @param message message to be displayed in pop up frame
     * @return Response model containing output data necessary
     */
    private DeletePostResponseModel deleteDatabase(DeletePostRequestModel requestModel, Post post, boolean success, String message){
        DeletePostResponseModel responseModel;
        if (success) {
            responseModel = new DeletePostResponseModel(true, requestModel.getIsTimer(), "");
            for (int favUser : post.getFavouritedUsers()) {
                this.dataAccess.removeFavourite(requestModel.getPostId(), favUser);
            }
            for (int comment : post.getReplies()){
                this.dataAccess.deleteComment(comment);
            }
            this.dataAccess.removeUser(requestModel.getPostId(), post.getUser());
            this.dataAccess.deletePost(requestModel.getPostId());
        } else {
            responseModel = new DeletePostResponseModel(false, requestModel.getIsTimer(), message);
        }

        return responseModel;
    }
}
