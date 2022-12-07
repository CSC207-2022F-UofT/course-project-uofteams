package view_comment.use_case;

import java.util.ArrayList;
import java.util.List;

public class ViewCommentInteractor implements ViewCommentInputBoundary {
    private final ViewCommentOutputBoundary presenter;
    private final ViewCommentDsGateway dataAccess;

    /**
     * Initialize the interactor for this use case.
     *
     * @param dataAccess The gateway to access the data repository.
     * @param presenter The presenter of this use case.
     */
    public ViewCommentInteractor(ViewCommentDsGateway dataAccess, ViewCommentOutputBoundary presenter) {
        this.presenter = presenter;
        this.dataAccess = dataAccess;

    }

    /**
     * Retrieves comments to present given input of post ID. With the help of findPostHelper and findCommentHelper.
     *
     * @param requestModel Data Structure of input data .
     */
    @Override
    public void retrieveComments(ViewCommentRequestModel requestModel) {
        try {
            int postId = requestModel.getPostId();
            List<String[]> postData = dataAccess.getAllPosts();
            List<String[]> commentData = dataAccess.getAllComments();
            String[] foundPost = findPostHelper(postData, postId);
            assert foundPost != null;
            String[] temp = foundPost[9].split(" ");
            ArrayList<String[]> foundComments = findCommentsHelper(commentData, temp);
            ViewCommentResponseModel outputData = new ViewCommentResponseModel(foundComments, "");
            this.presenter.present(outputData);

        } catch (ViewCommentException e) {

            ViewCommentResponseModel outputData = new ViewCommentResponseModel(null,"Given post" +
                    "have no replies");
            this.presenter.present(outputData);

        } catch (NullPointerException e){
            ViewCommentResponseModel outputData = new ViewCommentResponseModel(null,
                    "Could not find post");
            this.presenter.present(outputData);
        }

    }

    /**
     * Retrieves specific post with post ID from list of all posts
     *
     * @param postData data of all posts.
     * @param postId id of input post.
     *
     */

    private String[] findPostHelper(List<String[]> postData, int postId) {
        for (int x = 1; x < postData.size(); x++) {
            String currentPostId = postData.get(x)[0];
            if (Integer.parseInt(currentPostId) == postId) {
                return postData.get(x);
            }


        }
        return null;
    }

    /**
     * Retrieves replies of a specific post with post ID.
     *
     * @param commentData data of all comments.
     * @param Replies List of replies of a post.
     *
     */
    private ArrayList<String[]> findCommentsHelper(List<String[]> commentData, String[] Replies) throws ViewCommentException {
        ArrayList<String[]> returnList = new ArrayList<>();
        for (int x = 1; x < commentData.size(); x++) {
            String currentCommentId = commentData.get(x)[0];
            for (String s : Replies) {
                if (currentCommentId.equals(s)) {
                    returnList.add(commentData.get(x));
                    break;
                }

            }
        }
        if (returnList.isEmpty()){
            throw new ViewCommentException("given post has no replies");
        }
        return returnList;
    }
}