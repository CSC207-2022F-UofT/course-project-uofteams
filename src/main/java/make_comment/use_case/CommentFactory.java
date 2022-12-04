package make_comment.use_case;

import entities.Comment;

public class CommentFactory {

    //creates and returns a comment
    public Comment makeComment(int UserId, String body, int id){
        return new Comment(UserId, body, id);

    }
}