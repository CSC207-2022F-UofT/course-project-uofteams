package entities;

import java.util.Date;

/**
 * Comment extends abstract class Postable and inherits all of its
 * instance attributes (user, body, replies) and addComment method.
 *
 */
public class Comment extends Postable{

    /**
     * Initializes an instance of Comment.
     *
     * @param commenter The user that posted this comment.
     * @param body The text content of the comment.
     * @param id The unique id for this comment
     */
    public Comment(User commenter, String body, int id){
        super.user = commenter;
        super.body = body;
        super.creationDate = new Date().toString();
        super.id = id;
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
