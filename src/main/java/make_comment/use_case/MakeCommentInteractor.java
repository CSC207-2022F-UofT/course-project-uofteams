package make_comment.use_case;


import entities.Comment;
import entities.CurrentUser;
import java.util.*;

/**
 * The MakeCommentInteractor in the use case layer implements the MakeCommentInputBoundary interface.
 * It takes in information from the outer layers of the architecture to run the use case by working directly
 * with the entities and ultimately send information back out into the UI.
 */
public class MakeCommentInteractor implements MakeCommentInputBoundary {
    private final MakeCommentDsGateway dataAccess;
    private final MakeCommentOutputBoundary presenter;

    private final CommentFactory commentFactory;

    public MakeCommentInteractor(MakeCommentDsGateway dataAccess, MakeCommentOutputBoundary presenter, CommentFactory commentFactory) {
        this.dataAccess = dataAccess;
        this.presenter = presenter;
        this.commentFactory = commentFactory;
    }

    /**
     * constructAndSaveCommentAndUpdatePost uses helper methods to construct, store the comment and update the post
     * @param makeCommentRequestModel a class containing the data that is used to create the comment.
     *
     */
    @Override
    public void constructAndSaveCommentAndUpdatePost (MakeCommentRequestModel makeCommentRequestModel) {
        try {
            int userId = getCurrentUserID();
            String body = makeCommentRequestModel.getCommentBody();
            int commentId = getNumCommentCreated();
            int postId = makeCommentRequestModel.getPostId();
            constructAndSaveCommentHelper(userId, body, commentId);
            updatePostHelper(postId, commentId);
            MakeCommentResponseModel responseModel = new
                    MakeCommentResponseModel(true, "");
            this.presenter.present(responseModel);

        } catch (MakeCommentException e) {
            MakeCommentResponseModel responseModel = new
                    MakeCommentResponseModel(false, "Comment was not created");
            this.presenter.present(responseModel);
        }
    }
    @Override
    public int getCurrentUserID() {
        return CurrentUser.getCurrentUserId();
    }

    @Override
    public int getNumCommentCreated() {
        return this.dataAccess.getNumComments();
    }

    /**
     * updates the post and pass it to data Access to be stored
     * @param postId Id of the current post.
     * @param commentId unique identifier of this comment.
     * @throws MakeCommentException notifying constructAndSaveCommentAndUpdatePost when an error occurs when retrieving a post.
     */
    private void updatePostHelper(int postId, int commentId) throws MakeCommentException{
        try {
            List<String[]>  postData = dataAccess.getCurrentPosts();
            for(int x = 1; x < postData.size(); x++){
                String currentPostId = postData.get(x)[0];
                if (Integer.parseInt(currentPostId) == postId){
                    String newRepliesString;
                    if (postData.get(x)[9].equals("")){
                        newRepliesString =  Integer.toString(commentId);
                    } else {
                        newRepliesString = postData.get(x)[9] + (" " + commentId);
                    }

                    String[] newPost = {
                            postData.get(x)[0],
                            postData.get(x)[1],
                            postData.get(x)[2],
                            postData.get(x)[3],
                            postData.get(x)[4],
                            postData.get(x)[5],
                            postData.get(x)[6],
                            postData.get(x)[7],
                            postData.get(x)[8],
                            newRepliesString };
                    postData.remove(x);
                    postData.add(newPost);
                    break;

                }
            }
            dataAccess.updatePostDatabase(postData);

        } catch (NullPointerException e){
            throw new MakeCommentException("Comment was not created");
        }

    }

    /**
     * Create a new comment + updating the total number of comments and pass it into data access to be stored.
     * @param userId User making this comment.
     * @param body Text body of this comment .
     * @param commentId unique identifier of this comment.
     * @throws MakeCommentException notifying constructAndSaveCommentAndUpdatePost if the body of this comment is empty.
     */
    private void constructAndSaveCommentHelper(int userId, String body, int commentId) throws MakeCommentException {
        if (body.equals("")){
            throw new MakeCommentException("Message body is empty!");
        }
        Comment thisComment = commentFactory.makeComment(userId, body, commentId);
        String stringCreationDate = thisComment.getCreationDate().toString();
        Map<String, String> saveFormat = new HashMap<>();
        saveFormat.put("commentID", Integer.toString(commentId));
        saveFormat.put("commenterID", Integer.toString(userId));
        saveFormat.put("body", body);
        saveFormat.put("creationDate", stringCreationDate);
        this.dataAccess.saveComment(saveFormat);
        this.dataAccess.setNumComments(this.getNumCommentCreated() + 1);
    }


}