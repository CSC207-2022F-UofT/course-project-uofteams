package favourite.use_case;

public interface FavouriteOutputBoundary {
    /**
     * Presents the FavouriteResponseModel to the UI (FavouriteView)
     * @param responseModel carries message to be shared with the user in the UI
     */
    void present(FavouriteResponseModel responseModel);
}
