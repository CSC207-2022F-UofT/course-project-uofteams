package entities;



import java.time.LocalDate;

public abstract class Postable{
    // The access modifiers have been omitted so that the default settings would be applied.
    // If we make these instance attributes private they become inaccessible to the child classes.

    // the User who created the Postable
    int userID;
    // the main text content of the Postable

    String body;

    // the date this Postable was created
    LocalDate creationDate;

    // unique identifier for this postable
    int id;

    /**
     * Returns the id of this post.
     */
    public int getid(){return this.id;}

    /**
     * Returns the string representation of the date when which this Postable was created.
     */
    public LocalDate getCreationDate(){return this.creationDate;}

    /**
     * Returns the body (or text content) of this instance of Postable.
     */
    public String getBody(){
        return this.body;
    }

    /**
     * Returns the User that created this Postable.
     */
    public int getUser(){
        return this.userID;
    }

}

