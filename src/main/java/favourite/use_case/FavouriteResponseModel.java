package favourite.use_case;

/**
 * Class FavouriteResponseModel in the use case layer carries information from the FavouriteInteractor
 * to the UI about the outcome of the use case.
 */
public class FavouriteResponseModel {
    // Message corresponding to whether the post was newly added to favourites or not.
    private final String message;

    /**
     * Initializes FavouriteResponseModel
     *
     * @param message
     */
    public FavouriteResponseModel(String message){
        this.message = message;
    }

    /**
     * Returns the String message carried by FavouriteResponseModel
     *
     * @return String
     */
    public String getMessage(){
        return this.message;
    }
}
