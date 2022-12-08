package favourite.use_case;

/**
 * The output boundary for the Favourite use case, implemented by FavouritePresenter
 */
public interface FavouriteOutputBoundary {
    /**
     * Updates the view by calling the updateViewModel method in viewModel to trigger a property change
     * @param responseModel a FavouriteResponseModel object that carries booleans to trigger firePropertyChange
     */
    void present(FavouriteResponseModel responseModel);
}
