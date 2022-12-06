package favourite.interface_adapters;

import favourite.use_case.FavouriteOutputBoundary;
import favourite.use_case.FavouriteResponseModel;

/**
 * The presenter class of the Favourite use case
 */
public class FavouritePresenter implements FavouriteOutputBoundary {
    private final FavouriteViewModel viewModel;

    /**
     * Initializes the FavouritePresenter
     * @param viewModel a FavouriteViewModel object
     */
    public FavouritePresenter(FavouriteViewModel viewModel){
        this.viewModel = viewModel;
    }

    /**
     * Updates the view by calling the updateViewModel method in viewModel to trigger a property change
     * @param responseModel a FavouriteResponseModel object that carries booleans to trigger firePropertyChange
     */
    @Override
    public void present(FavouriteResponseModel responseModel) {
        boolean favourited = responseModel.getFavouritedBool();
        boolean unfavourited = responseModel.getUnfavouritedBool();
        viewModel.updateViewModel(favourited, unfavourited);
    }
}
