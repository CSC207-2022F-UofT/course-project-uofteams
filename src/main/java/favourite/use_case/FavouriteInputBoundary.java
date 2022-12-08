package favourite.use_case;

/**
 * The input boundary for the Favourite use case which is implemented by FavouriteInteractor
 */
public interface FavouriteInputBoundary {
    /**
     * Favourites a post if the user has not already, unfavourite a post if the user has favourited it already.
     * @param requestModel a FavouriteRequestModel object that carries information about the post being
     *                     favourited/unfavourited
     */
    void favouritePost(FavouriteRequestModel requestModel);
}
