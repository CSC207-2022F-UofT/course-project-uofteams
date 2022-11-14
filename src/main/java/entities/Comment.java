package entities;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Comment extends abstract class Postable and inherits all of its
 * instance attributes (user, body, replies) and addComment method.
 *
 */
public class Comment extends Postable{
    // number of Comments instantiated
    public static int numComments = 0;
    // id is the unique identifier of Comment, equal to number of Comments at time of instantiation
    private final int id;

    /**
     * Initializes an instance of Comment.
     *
     * @param commenter The user that posted this comment.
     * @param body The text content of the comment.
     * @param numCommentsCreated The number of comments created in the program so far.
     */
    public Comment(User commenter, String body, int numCommentsCreated){
        super.user = commenter;
        super.body = body;
        super.replies = new ArrayList<>();
        super.creationDate = new Date();
        Comment.numComments = numCommentsCreated;
        Comment.numComments ++;
        this.id = Comment.numComments;
    }

    /**
     * Return if Comment is equal to o
     *
     * @param o The Object to be compared to
     * @return boolean representing whether Comment is equal to o
     * */
    @Override
    public boolean equals (Object o) {
        if (o == null) {
            return false;
        } else if (!(o instanceof Comment)) {
            return false;
        }  else {
            return (this.id == ((Comment) o).id);
        }
    }
}
