package entities;

import java.util.List;
import java.util.Date;

abstract class Postable{
    // The access modifiers have been omitted so that the default settings would be applied.
    // If we make these instance attributes private they become inaccessible to the child classes.
    User user;
    String body;
    List<Postable> replies;
    Date creationDate;

    /**
     * Adds a comment to the replies of the given instance of Postable.
     *
     * @param comment the comment to be added to replies
     */
    public void addComment(Comment comment){
        this.replies.add(comment);
    }

    /**
     * Returns the list of replies to a Postable object.
     */
    public List<Postable> getReplies(){return this.replies;}

    /**
     * Returns the date of which this Postable was created.
     */
    public Date getCreationDate(){return this.creationDate;}

}

