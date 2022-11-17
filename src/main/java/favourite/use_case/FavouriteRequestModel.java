package favourite.use_case;

import entities.User;
import entities.Post;

public class FavouriteRequestModel {
    public Post post;
    public User user;

    /**
     * Initializes a FavouriteRequestModel
     *
     * @param post the post being favourited
     * @param user the user favouriting the post
     */
    public FavouriteRequestModel(FavouriteDataAccessInterface dataAccess){
        this.post = dataAccess.getPost();
        this.user = dataAccess.getUser();
    }
}
