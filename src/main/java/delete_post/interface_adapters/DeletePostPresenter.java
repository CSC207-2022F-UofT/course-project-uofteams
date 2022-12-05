package delete_post.interface_adapters;

import delete_post.use_case.DeletePostOutputBoundary;
import delete_post.use_case.DeletePostResponseModel;
import delete_post.UI.DeleteView;

import javax.swing.JFrame;

public class DeletePostPresenter implements DeletePostOutputBoundary{

    private final DeletePostViewModel viewModel;

    public DeletePostPresenter(DeletePostViewModel viewModel){
        this.viewModel = viewModel;
    }
    @Override
    public void prepareFailView(){
        viewModel.updateViewModel(false, true);
    }
    public void prepareSuccessView(DeletePostResponseModel responseModel){
        viewModel.updateViewModel(true, true);
    }
    public void prepareTimerView(DeletePostResponseModel responseModel){}
}
