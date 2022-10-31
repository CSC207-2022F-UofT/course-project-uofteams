package entities;

import java.util.List;

abstract class Postable{
    User user;
    String body;
    List<Postable> replies;

    public void addComment(Comment comment){
        this.replies.append(comment);
    }
}

