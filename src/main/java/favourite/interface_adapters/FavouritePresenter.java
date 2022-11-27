package favourite.interface_adapters;

import favourite.use_case.FavouriteOutputBoundary;
import favourite.use_case.FavouriteResponseModel;

public class FavouritePresenter implements FavouriteOutputBoundary {
    private final FavouriteViewModel viewModel;

    public FavouritePresenter(FavouriteViewModel viewModel){
        this.viewModel = viewModel;
    }

    @Override
    public void present(FavouriteResponseModel responseModel) {
        boolean favourited = responseModel.getFavouritedBool();
        boolean unfavourited = responseModel.getUnfavouritedBool();
        viewModel.updateViewModel(favourited, unfavourited);
    }
}
