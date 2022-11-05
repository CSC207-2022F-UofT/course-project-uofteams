package entities;

import java.util.Date;
import java.util.List;

import java.security.PublicKey;


public class Comment extends Postable{

    public static Date creationDate;

    public Comment(User poster, String body, List<Comment> replies){
        super.user = poster;
        super.body = body;
        super.replies = replies;
        creationDate = new Date();
    }


}
