package make_comment.use_case;

import entities.Comment;
import entities.User;

public class CommentFactory {

    //creates and returns a comment

    public static Comment makeComment(int UserId, String body, int id) {
        return new Comment(UserId, body, id);

    }
}