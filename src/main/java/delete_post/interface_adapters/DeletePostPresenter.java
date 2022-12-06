package delete_post.interface_adapters;

import delete_post.use_case.DeletePostOutputBoundary;
import delete_post.use_case.DeletePostResponseModel;
import entities.User;

public class DeletePostPresenter implements DeletePostOutputBoundary{

    private final DeletePostViewModel viewModel;

    public DeletePostPresenter(DeletePostViewModel viewModel){
        this.viewModel = viewModel;
    }
    @Override
    public void prepareFailView(String error){
        //error popup, no change to posts view or database;
    }
    public void prepareSuccessView(DeletePostResponseModel responseModel){
        //update view posts view
    }
}