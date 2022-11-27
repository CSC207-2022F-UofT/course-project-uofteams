package favourite.use_case;

public interface FavouriteInputBoundary {
    /**
     * Favourites or unfavourites a post.
     *
     * @param requestModel carries information about the post being favourited/unfavourited
     * @return FavouriteResponseModel
     */
    void favouritepost(FavouriteRequestModel requestModel);
}
