package favourite.use_case;

/**
 * The request model data structure class for the Favourite use case
 */
public class FavouriteRequestModel {
    private int postID;

    /**
     * Initializes a FavouriteRequestModel
     *
     * @param postID the post being favourited
     * */
    public FavouriteRequestModel(int postID){
        this.postID = postID;
    }

    /**
     * Returns the id of the Post being favourited/unfavourited
     *
     * @return the integer ID of a post
     */
    public int getPostId(){
        return this.postID;
    }
}
