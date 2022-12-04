package favourite.use_case;

/**
 * The response model data structure class for the Favourite use case.
 */
public class FavouriteResponseModel {
    // true if the post was "favourited"
    private final boolean favourited;
    // true if the post was "unfavourited"
    private final boolean unfavourited;

    /**
     * Initializes FavouriteResponseModel
     *
     * @param favourited a boolean that is true when the post was favourited
     * @param unfavourited a boolean that is true when the post was unfavourited
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
