package favourite.interface_adapters;

import favourite.use_case.FavouriteInputBoundary;
import favourite.use_case.FavouriteRequestModel;

/**
 * The controller class of the Favourite use case
 */
public class FavouriteController{
    private final FavouriteInputBoundary interactor;

    /**
     * Initializes FavouriteController
     * @param interactor a FavouriteInteractor object passing as an instance of FavouriteInputBoundary
     */
    public FavouriteController (FavouriteInputBoundary interactor){
        this.interactor = interactor;
    }

    /**
     * Creates FavouriteRequestModel and runs the favourite use case
     * @param postID the integer ID of the post being favrourited/unfavourited
     */
    public void favourite(int postID){
        FavouriteRequestModel requestModel = new FavouriteRequestModel(postID);
        interactor.favouritePost(requestModel);
    }



}
