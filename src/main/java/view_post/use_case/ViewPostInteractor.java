package view_post.use_case;

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
     * Display the selected post.
     * @param requestModel The input data from the user.
     */
    @Override
    public void displayPost(ViewPostRequestModel requestModel) {
        int postID = requestModel.getPostID();
        String[] postInfo = dsGateway.getPostInfo(postID);

        // creating a List of Integers of ids of the replies (Comments) made on that post
        String[] replyids = postInfo[3].split(" ");

        String[] tags = postInfo[2].split(" ");

        ViewPostResponseModel outputData = new ViewPostResponseModel(postInfo[0], postInfo[1], tags,
                replyids, postInfo[4], postInfo[5], postInfo[6], postID, postInfo[8]);

        presenter.updateActivePost(outputData);
    }
}
