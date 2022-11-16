package entities;

import java.util.List;
import java.util.Date;

public abstract class Postable{
    // The access modifiers have been omitted so that the default settings would be applied.
    // If we make these instance attributes private they become inaccessible to the child classes.

    // the User who created the Postable
    User user;
    // the main text content of the Postable
    String body;
    // a list of Postable objects created as a reply to this Postable
    List<Postable> replies;
    // the date this Postable was created
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

    /**
     * Returns the body (or text content) of this instance of Postable.
     */
    public String getBody(){
        return this.body;
    }

    /**
     * Returns the User that created this Postable.
     */
    public User getUser(){
        return this.user;
    }

}

