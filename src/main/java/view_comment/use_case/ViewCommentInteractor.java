package view_comment.use_case;

import java.util.ArrayList;
import java.util.List;

public class ViewCommentInteractor implements ViewCommentInputBoundary {
    private final ViewCommentOutputBoundary presenter;
    private final ViewCommentDsGateway dataAccess;


    public ViewCommentInteractor(ViewCommentDsGateway dataAccess, ViewCommentOutputBoundary presenter) {
        this.presenter = presenter;
        this.dataAccess = dataAccess;

    }

    @Override
    public void retrieveComments(ViewCommentRequestModel requestModel) {
        try {
            int postId = requestModel.getPostId();
            List<String[]> postData = dataAccess.getAllPosts();
            List<String[]> commentData = dataAccess.getAllComments();
            String[] foundPost = findPostHelper(postData, postId);
            String[] temp = foundPost[9].split(" ");
            ArrayList<String[]> foundComments = findCommentsHelper(commentData, temp);
            ViewCommentResponseModel outputData = new ViewCommentResponseModel(foundComments, "");
            this.presenter.present(outputData);

        } catch (ViewCommentException e) {

            ArrayList<String[]> foundComments = null;
            ViewCommentResponseModel outputData = new ViewCommentResponseModel(foundComments,"Given post" +
                    "have no replies");
            this.presenter.present(outputData);
        } catch (ViewCommentNoPostException e){
            ArrayList<String[]> foundComments = null;
            ViewCommentResponseModel outputData = new ViewCommentResponseModel(foundComments,
                    "Could not find post");
            this.presenter.present(outputData);
        }

    }

    private String[] findPostHelper(List<String[]> postData, int postId) throws ViewCommentNoPostException {
        for (int x = 1; x < postData.size(); x++) {
            String currentPostId = postData.get(x)[0];
            if (Integer.parseInt(currentPostId) == postId) {
                return postData.get(x);
            }
        }
        throw new ViewCommentNoPostException("Could not find post");


    }


    private ArrayList<String[]> findCommentsHelper(List<String[]> commentData, String[] temp) throws ViewCommentException {
        ArrayList<String[]> returnList = new ArrayList<>();
        for (int x = 1; x < commentData.size(); x++) {
            String currentCommentId = commentData.get(x)[0];
            for (String s : temp) {
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