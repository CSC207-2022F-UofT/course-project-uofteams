package make_comment.use_case;


import entities.Comment;
import entities.CurrentUser;
import entities.Post;
import make_comment.driver.MakeCommentDatabaseAccess;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The MakeCommentInteractor in the use case layer implements the MakeCommentInputBoundary interface.
 * It takes in information from the outer layers of the architecture to run the use case by working directly
 * with the entities and ultimately send information back out into the UI.
 */
public class MakeCommentInteractor implements MakeCommentInputBoundary {
    private final MakeCommentDatabaseAccess dataAccess;

    private final CommentFactory commentFactory;

    public MakeCommentInteractor(MakeCommentDatabaseAccess dataAccess) {
        this.dataAccess = dataAccess;
        this.commentFactory = new CommentFactory();
    }

    @Override
    public void constructAndSaveCommentAndUpdatePost (MakeCommentRequestModel mCRM){
        int userId = mCRM.getUserId();
        String body = mCRM.getCommentBody();
        int commentId = mCRM.getSelfId();
        int postId = mCRM.getPostId();
        constructAndSaveCommentHelper(userId, body, commentId);
        updatePostHelper(postId, commentId);

    }

    @Override
    public int getCurrentUserID() {
        return CurrentUser.getCurrentUserId();
    }

    @Override
    public int getNumCommentCreated() {
        return this.dataAccess.getNumComments();
    }

    private void updatePostHelper(int postId, int commentId){
        List<String[]>  postData = dataAccess.getCurrentPosts();
        Map<String, String> foundPost = new HashMap<>();
        for(String[] post: postData){
            String currentPostId = post[0];
            if (Integer.parseInt(currentPostId) == postId){
                String newRepliesString = post[9] + Integer.toString(commentId);
                foundPost.put("postID", post[0]);
                foundPost.put("posterID", post[1]);
                foundPost.put("title", post[2]);
                foundPost.put("mainDescription", post[3]);
                foundPost.put("tags", post[4]);
                foundPost.put("collaborators", post[5]);
                foundPost.put("deadline", post[6]);
                foundPost.put("creationDate", post[7]);
                foundPost.put("favouritedUsersIDs", post[8]);
                foundPost.put("repliesIDs", newRepliesString);
            }
        }


    }

    private void constructAndSaveCommentHelper(int userId, String body, int commentId){
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