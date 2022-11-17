package favourite.interface_adapters;

import favourite.drivers.DataAccess;
import favourite.ui.FavouriteView;
import favourite.use_case.*;

public class FavouriteViewModel implements FavouriteOutputBoundary {
    private final FavouriteDataAccessInterface dataAccess;
    private final View view;
    private final FavouriteInputBoundary inputBoundary;

    public FavouriteViewModel(FavouriteDataAccessInterface dataAccess, View view){
        this.dataAccess = dataAccess;
        this.view = view;
        this.inputBoundary = new FavouriteInteractor(dataAccess);
    }

    public void favourite(){
        FavouriteRequestModel requestModel = new FavouriteRequestModel(this.dataAccess);
        present(inputBoundary.favouritepost(requestModel));
    }

    @Override
    public void present(FavouriteResponseModel responseModel){
        view.update();
    }

}
