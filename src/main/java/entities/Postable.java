package entities;

import java.util.List;

abstract class Postable{
    User user;
    String body;

    List<Comment> replies;

    public void addComment(Comment comment){
        this.replies.add(comment);
    }
}

