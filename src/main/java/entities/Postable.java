package entities;

import java.util.List;

public abstract class Postable{
    User user;
    String body;

    List<Comment> replies;

    public void addComment(Comment comment){
        this.replies.add(comment);
    }
}

