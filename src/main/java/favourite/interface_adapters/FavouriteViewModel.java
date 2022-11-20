package favourite.interface_adapters;

import favourite.drivers.DataAccess;
import favourite.use_case.*;

/**
 * FavouriteViewModel in the interface adapters layer implements the FavouriteOutputBoundary interface.
 * It runs the use case and interacts with the use case layer in response to a user's interaction with FavouriteView.
 */
public class FavouriteViewModel implements FavouriteOutputBoundary {
    private final FavouriteDSGateway dataAccess;
    private final View view;
    private final FavouriteInputBoundary inputBoundary;

    /**
     * Initializes FavouriteViewModel
     *
     * @param view
     */
    public FavouriteViewModel(View view){
        this.dataAccess = new DataAccess(new PostFactory(), new UserFactory(),
                new PostStringFactory(), new UserStringFactory());
        this.view = view;
        this.inputBoundary = new FavouriteInteractor(dataAccess);
    }

    /**
     * Creates FavouriteRequestModel and runs the favourite use case
     *
     * @param postid id of the post being favrourited/unfavourited
     */
    public void favourite(int postid){
        FavouriteRequestModel requestModel = new FavouriteRequestModel(postid);
        present(inputBoundary.favouritepost(requestModel));
    }

    @Override
    public void present(FavouriteResponseModel responseModel){
        view.update(responseModel);
    }

}
