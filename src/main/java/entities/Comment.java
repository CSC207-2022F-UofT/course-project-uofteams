package entities;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.security.PublicKey;


public class Comment extends Postable{
    // Class Comment extends abstract class Postable and inherits all of its instance
    // attributes (user, body, replies) and addComment method
    // (which has already been implemented)
    public static Date creationDate;

    public Comment(User commenter, String body){
        super.user = commenter;
        super.body = body;
        super.replies = new ArrayList<>();
        creationDate = new Date();

    }
    public String getBody(){
        return super.body;
    }

    public User getUser(){
        return super.user;
    }

//test



}
