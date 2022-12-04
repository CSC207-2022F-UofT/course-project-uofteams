package view_post.use_case;

import view_post.use_case.view_post_exceptions.ViewPostException;

/**
 * The interactor class for the View Post use case.
 */
public class ViewPostInteractor implements ViewPostInputBoundary {
    private final ViewPostDsGateway dsGateway;
    private final ViewPostOutputBoundary presenter;

    /**
     * Initialize the interactor for this use case.
     *
     * @param dsGateway The gateway to access the data repository.
     * @param presenter The presenter of this use case.
     */
    public ViewPostInteractor(ViewPostDsGateway dsGateway, ViewPostOutputBoundary presenter) {
        this.dsGateway = dsGateway;
        this.presenter = presenter;
    }

    /**
     * Displays the selected post.
     * @param requestModel The input data from the user.
     */
    @Override
    public void displayPost(ViewPostRequestModel requestModel) {
        int postID = requestModel.getPostID();

        try {
            String[] postInfo = dsGateway.getPostInfo(postID);

            String[] tags = postInfo[2].split(" ");

            ViewPostResponseModel outputData = new ViewPostResponseModel(postInfo[0], postInfo[1], tags,
                    postInfo[4], postInfo[5], postInfo[6], postID, postInfo[8]);

            presenter.updateActivePost(outputData);
        }
        catch(ViewPostException v){
            ViewPostResponseModel outputData = new ViewPostResponseModel("", "", new String[]{""},
                    "", "", "", -1, "");
            presenter.updateActivePost(outputData);
        }
    }
}
