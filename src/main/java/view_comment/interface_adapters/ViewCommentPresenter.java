package view_comment.interface_adapters;

import view_comment.use_case.ViewCommentOutputBoundary;
import view_comment.use_case.ViewCommentResponseModel;

import java.util.ArrayList;

public class ViewCommentPresenter implements ViewCommentOutputBoundary {
    private final ViewCommentViewModel viewModel;

    public ViewCommentPresenter(ViewCommentViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void present(ViewCommentResponseModel responseModel) {
        ArrayList<String[]> commentInfo = responseModel.getOutputComments();
        ArrayList<String> tempBodys = new ArrayList<>();
        ArrayList<String> tempCommentator = new ArrayList<>();
        ArrayList<String> tempCreationDate = new ArrayList<>();
        if(responseModel.isReplies()){
            for (String[] comment: commentInfo) {
                tempCommentator.add(comment[1]);
                tempBodys.add((comment[2]));
                tempCreationDate.add(comment[3]);
            }
        }

        viewModel.updateViewModel(responseModel.isReplies(), responseModel.getErrorMessage(),
                tempBodys, tempCommentator, tempCreationDate);


    }
}
