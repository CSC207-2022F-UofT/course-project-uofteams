package delete_post.use_case;

import delete_post.interface_adapters.DeletePostPresenter;
import entities.User;

public class DeletePostInteractor implements DeletePostInputBoundary{

    private final DeletePostPresenter presenter;
    private final DeletePostDsGateway dataAccess;

    public DeletePostInteractor(DeletePostPresenter presenter, DeletePostDsGateway dataAccess){
        this.presenter = presenter;
        this.dataAccess = dataAccess;
    }

    @Override
    public void delete(DeletePostRequestModel requestModel){
        if (requestModel.getIsTimer() || requestModel.getIsAdmin() ||
                requestModel.getUser() == (requestModel.getPostUser())){
            // delete post
            DeletePostResponseModel responseModel = new DeletePostResponseModel(requestModel.getPost());

            for (User favouriteId: responseModel.getFavourites()){
                this.dataAccess.removeFavourite(responseModel.getId(), favouriteId);
            }

            for (String tag: responseModel.getTags()){
                this.dataAccess.removeTag(responseModel.getId(), tag);
            }

            this.dataAccess.deletePost(responseModel.getId());
            this.presenter.prepareSuccessView(responseModel);
            }

        this.presenter.prepareFailView("You do not have permission to delete this post.");
    }
}
