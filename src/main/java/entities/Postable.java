package entities;


public abstract class Postable{
    // The access modifiers have been omitted so that the default settings would be applied.
    // If we make these instance attributes private they become inaccessible to the child classes.

    // the User who created the Postable
    User user;
    // the main text content of the Postable
    String body;

    // the date this Postable was created
    String creationDate;

    // unique identifier for this postable
    int id;

    /**
     * Returns the date of which this Postable was created.
     */
    public int getid(){return this.id;}

    /**
     * Returns the date of which this Postable was created.
     */
    public String getCreationDate(){return this.creationDate;}

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

