package favourite.use_case;

/**
 * Class FavouriteResponseModel in the use case layer carries information from the FavouriteInteractor
 * to the UI about the outcome of the use case.
 */
public class FavouriteResponseModel {
    // true if the post was "favourited"
    private final boolean favourited;
    // true if the post was "unfavourited"
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
     * Returns the boolean value of instance var favourited
     *
     * @return boolean that expresses whether the post was "favourited"
     */
    public boolean getFavouritedBool(){
        return this.favourited;
    }

    /**
     * Returns the boolean value of instance var unfavourited
     *
     * @return boolean that expresses whether the post was "unfavourited"
     */
    public boolean getUnfavouritedBool(){return this.unfavourited;}
}
