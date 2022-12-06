package make_comment.use_case;

import entities.Comment;

public class CommentFactory {

    /**
     * Creates and returns a comment.
     * @param UserId the unique identifier of the user that made the comment
     * @param body the text body of the comment
     * @param id the unique identifier of this comment
     */
    public Comment makeComment(int UserId, String body, int id){
        return new Comment(UserId, body, id);

    }
}