package favourite.use_case;

public class FavouriteResponseModel {
    // Message corresponding to whether the post was newly added to favourites or not.
    public String message;

    /**
     * Initializes FavouriteResponseModel
     *
     * @param message
     */
    public FavouriteResponseModel(String message){
        this.message = message;
    }
}
