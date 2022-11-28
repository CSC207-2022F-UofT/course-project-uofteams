package make_comment.use_case;

import entities.Comment;
import make_comment.driver.MakeCommentDatabaseAccess;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class MakeCommentInteractor implements MakeCommentInputBoundary {
    private final MakeCommentDatabaseAccess dataAccess;

    public MakeCommentInteractor(MakeCommentDatabaseAccess dataAccess) {
        this.dataAccess = dataAccess;
    }

    @Override
    public void constructAndSaveComment (MakeCommentRequestModel mCRM){
        int userId = mCRM.getUserId();
        String body = mCRM.getCommentBody();
        int commentId = mCRM.getSelfId();
        Comment thisComment = CommentFactory.makeComment(userId, body, commentId);
        String stringCreationDate = thisComment.getCreationDate().toString();
        Date creationDate = new Date();
        Map<String, String> saveFormat = new HashMap<>();
        saveFormat.put("commentID", Integer.toString(commentId));
        saveFormat.put("commenterID", Integer.toString(userId));
        saveFormat.put("body", body);
        saveFormat.put("creationDate", stringCreationDate);
        this.dataAccess.saveComment(saveFormat);
        this.dataAccess.setNumComments(this.getNumCommentCreated() + 1);
    }

    @Override
    public int getCurrentUserID() {
        return this.dataAccess.getCurrentUserid();
    }

    @Override
    public int getNumCommentCreated() {
        return this.dataAccess.getNumComments();
    }

    @Override
    public void updatePost(MakeCommentRequestModel mCRM) {
        int userId = mCRM.getUserId();
        String body = mCRM.getCommentBody();
        int commentId = mCRM.getSelfId();
        Comment thisComment = CommentFactory.makeComment(userId, body, commentId);
        String stringCreationDate = thisComment.getCreationDate().toString();
        Date creationDate = new Date();
        Map<String, String> saveFormat = new HashMap<>();
        saveFormat.put("commentID", Integer.toString(commentId));
        saveFormat.put("commenterID", Integer.toString(userId));
        saveFormat.put("body", body);
        saveFormat.put("creationDate", stringCreationDate);
        this.dataAccess.saveComment(saveFormat);
        this.dataAccess.setNumComments(this.getNumCommentCreated() + 1);

    }
}