package favourite.use_case;

import entities.User;
import entities.Post;

/**
 * Class FavouriteRequestModel in the use case layer carries information about the post being liked from the UI
 * to the FavouriteInteractor
 */
public class FavouriteRequestModel {
    private int postid;

    /**
     * Initializes a FavouriteRequestModel
     *
     * @param postid the post being favourited
     * */
    public FavouriteRequestModel(int postid){
        this.postid = postid;
    }

    /**
     * Returns the id of the Post being favourited/unfavourited
     *
     * @return int
     */
    public int getUserId(){
        return this.postid;
    }
}
