package delete_post.use_case;

import entities.CurrentUser;
import entities.Post;
import entities.User;

import java.util.List;

public class DeletePostResponseModel {

    private Post post;

    public DeletePostResponseModel(Post post){
        this.post = post;
    }

    public int getUserId(){
        return post.getUserID();
    }

    public int getId(){
        return post.getID();
    }

    public List<User> getFavourites(){
        return post.getFavouritedUsers();
    }

    public List<String> getTags(){
        return post.getTags();
    }
}