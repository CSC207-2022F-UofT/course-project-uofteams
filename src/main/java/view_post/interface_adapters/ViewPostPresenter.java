package view_post.interface_adapters;

import view_post.use_case.ViewPostOutputBoundary;
import view_post.use_case.ViewPostResponseModel;

import java.util.ArrayList;

public class ViewPostPresenter implements ViewPostOutputBoundary {
    private ViewPostViewModel viewModel;

    public ViewPostPresenter(ViewPostViewModel viewModel){
        this.viewModel = viewModel;
    }
    @Override
    public void updateActivePost(ViewPostResponseModel responseModel) {
        String postEmail = responseModel.getPosterEmail();
        String postBody = responseModel.getPostBody();
        String postTags = responseModel.getPostTags();
        ArrayList<Integer> postReplies = responseModel.getPostReplies();
        String deadline = responseModel.getDeadline();
        String creationDate = responseModel.getCreationDate();
        String collaborators = responseModel.getCollaborators();
        int postID = responseModel.getPostID();
        String title = responseModel.getTitle();

        ViewPostOutputData outputData = new ViewPostOutputData(postEmail, postBody, postTags, postReplies, deadline,
                creationDate, collaborators, postID, title);

        viewModel.updateView(outputData);
    }
}
