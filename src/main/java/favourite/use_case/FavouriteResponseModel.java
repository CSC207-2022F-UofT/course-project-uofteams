package favourite.use_case;

/**
 * Class FavouriteResponseModel in the use case layer carries information from the FavouriteInteractor
 * to the UI about the outcome of the use case.
 */
public class FavouriteResponseModel {
    // Message corresponding to whether the post was newly added to favourites or not.
    private final boolean favourited;
    private final boolean unfavourited;

    /**
     * Initializes FavouriteResponseModel
     *
     * @param message
     */
    public FavouriteResponseModel(boolean favourited, boolean unfavourited){

        this.favourited = favourited;
        this.unfavourited = unfavourited;
    }

    /**
     * Returns the String message carried by FavouriteResponseModel
     *
     * @return String
     */
    public boolean getFavouritedBool(){
        return this.favourited;
    }

    public boolean getUnfavouritedBool(){return this.unfavourited;}
}
