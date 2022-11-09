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

    public void addComment(Comment comment){
        this.replies.append(comment);
    }

}

