package entities;

public class Comment extends Postable{
    // Class Comment extends abstract class Postable and inherits all of its instance
    // (user, body, replies) and addComment method (which has already been implemented)
    public Comment(User commenter, String comment){
        super.user = commenter;
        super.body = comment;
    }
}
