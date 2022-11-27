package favourite.interface_adapters;

import favourite.use_case.FavouriteOutputBoundary;
import favourite.use_case.FavouriteResponseModel;

public class FavouritePresenter implements FavouriteOutputBoundary {
    private final FavouriteViewModel viewModel;

    /**
     * Initializes the FavouritePresenter
     * @param viewModel takes FavouriteViewModel as a parameter
     */
    public FavouritePresenter(FavouriteViewModel viewModel){
        this.viewModel = viewModel;
    }

    /**
     * Updates the view by calling the updateViewModel method in veiwModel to trigger a property change
     * @param responseModel carries booleans to trigger firePropertyChange
     */
    @Override
    public void present(FavouriteResponseModel responseModel) {
        boolean favourited = responseModel.getFavouritedBool();
        boolean unfavourited = responseModel.getUnfavouritedBool();
        viewModel.updateViewModel(favourited, unfavourited);
    }
}
