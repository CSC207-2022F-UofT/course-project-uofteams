package delete_post.use_case;

import delete_post.interface_adapters.DeletePostPresenter;

public class DeletePostInteractor implements DeletePostInputBoundary{

    private final DeletePostOutputBoundary outputBoundary;
    private final DeletePostDsGateway dataAccess;

    public DeletePostInteractor(DeletePostOutputBoundary outputBoundary, DeletePostDsGateway dataAccess){
        this.outputBoundary = outputBoundary;
        this.dataAccess = dataAccess;
    }

    @Override
    public void delete(DeletePostRequestModel requestModel){
        if (requestModel.getIsTimer()){
            DeletePostResponseModel responseModel = new DeletePostResponseModel(requestModel.getPostId());

            this.dataAccess.removeFavourites(requestModel.getPostId());
            this.dataAccess.deletePost(requestModel.getPostId());
            this.outputBoundary.prepareTimerView(responseModel);
        }
        else if (requestModel.getIsAdmin() ||
                requestModel.getUserId() == this.dataAccess.getPostUser(requestModel.getPostId())){
            // delete post
            DeletePostResponseModel responseModel = new DeletePostResponseModel(requestModel.getPostId());

            this.dataAccess.removeFavourites(requestModel.getPostId());
            this.dataAccess.deletePost(requestModel.getPostId());
            this.outputBoundary.prepareSuccessView(responseModel);
            }
        else{
            this.outputBoundary.prepareFailView();
        }
    }
}
