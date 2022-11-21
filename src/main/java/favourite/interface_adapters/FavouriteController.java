package favourite.interface_adapters;

import favourite.use_case.FavouriteInputBoundary;
import favourite.use_case.FavouriteRequestModel;

public class FavouriteController{
    private final FavouriteInputBoundary interactor;
    private final int postID;

    /**
     * Initializes FavouriteController
     * @param interactor the interactor, passing as a FavouriteInputBoundary
     * @param postID the id of the post being favourtied/unfavourited
     */
    public FavouriteController (FavouriteInputBoundary interactor, int postID){
        this.interactor = interactor;
        this.postID = postID;
    }

    /**
     * Creates FavouriteRequestModel and runs the favourite use case
     *
     * @param postid id of the post being favrourited/unfavourited
     */
    public void favourite(int postid){
        FavouriteRequestModel requestModel = new FavouriteRequestModel(postid);
        interactor.favouritepost(requestModel);
    }



}
