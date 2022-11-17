package favourite.use_case;

import entities.Post;
import entities.User;

public class FavouriteInteractor implements FavouriteInputBoundary{
    private final FavouriteDataAccessInterface dataAccess;

    public FavouriteInteractor(FavouriteDataAccessInterface dataAccess){
        this.dataAccess = dataAccess;
    }

    @Override
    public FavouriteResponseModel favouritepost(FavouriteRequestModel requestModel) {
        boolean favourited = dataAccess.checkIfFavourited(requestModel.post, requestModel.user);
        if (favourited){
            return new FavouriteResponseModel("This post has already been added to your favourites!");
        }
        else{
            this.recordpost(requestModel.post, requestModel.user);
            this.recorduser(requestModel.post, requestModel.user);
            return new FavouriteResponseModel("This post has been successfully added to your favourites!");
        }
    }

    private void recorduser(Post post, User user){
        post.addFavouritedUser(user);
    }

    private void recordpost(Post post, User user){
        user.addFavourite(post);
    }
}
