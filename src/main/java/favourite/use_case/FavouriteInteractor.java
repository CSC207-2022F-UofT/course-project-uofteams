package favourite.use_case;

import entities.Post;
import entities.User;

import java.util.List;

/**
 * The FavouriteInteractor in the use case layer implements the FavouriteInputBoundary interface.
 * It takes in information from the outer layers of the architecture to run the use case by working directly
 * with the entities and ultimately send information back out into the UI.
 */
public class FavouriteInteractor implements FavouriteInputBoundary{
    private final FavouriteDSGateway dataAccess;

    /**
     * Initializes FavouriteInteractor
     *
     * @param dataAccess tells the FavouriteInteractor which post is being favourited/unfavourited
     */
    public FavouriteInteractor(FavouriteDSGateway dataAccess){
        this.dataAccess = dataAccess;
    }

    /**
     * Favourites a post if the user has not already, unfavourites a post if the user has favourited it already.
     *
     * @param requestModel tells the FavouriteInteractor which post is being favourited/unfavourited
     *                     by which user
     * @return a FavouriteResponseModel with an update message that should be presented to the user
     * by the view
     */
    @Override
    public FavouriteResponseModel favouritepost(FavouriteRequestModel requestModel) {
        User user = this.getUser();
        Post post = this.getPost(requestModel);

        // Checking if the user has already favourited this post
        boolean favourited = this.checkIfFavourited(post, user);

        // Unfavouriting post
        if (favourited){
            this.unfavourite(post, user);
            this.updatePost(post, requestModel.getUserId());
            return new FavouriteResponseModel("This post has been removed from your favourites.");
        }
        // Favouriting post
        else{
            this.favourite(post, user);
            this.unfavourite(post, user);
            this.updatePost(post, requestModel.getUserId());
            return new FavouriteResponseModel("This post has been successfully added to your favourites!");
        }
    }
    private User getUser(){
        return this.dataAccess.getUser();
    }

    private Post getPost(FavouriteRequestModel requestModel){
        return this.dataAccess.getPost(requestModel.getUserId());
    }

    private void updateUser(User user){
        this.dataAccess.saveUserInfo(user);
    }

    private void updatePost(Post post, int postid){
        this.dataAccess.savePostInfo(post, postid);
    }


    /**
     * Checks whether this user has already added to this user's favourites.
     *
     * @param post post that this user is trying to favourite
     * @param user user that is trying to favourite this post
     * @return true if this user has already favourited this post, false otherwise
     */
    public boolean checkIfFavourited(Post post, User user){
        List<Post> favourites = user.getFavourites();
        return (favourites.contains(post.getID()));
    }

    /**
     * Adds user to the post's list of favourited users, and adds post to the user's list of favourited posts
     *
     * @param post post that this user is trying to favourite
     * @param user user that is trying to favourite this post
     */
    private void favourite(Post post, User user){
        post.addFavouritedUser(user.getId()); // need to update entities to remove error line
        user.addFavourite(post.getID()); // need to update entities to remove error line
    }

    /**
     * Removes user from the post's list of favourited users, and removes post from the user's list of favourited posts
     *
     * @param post post that is being unfavourited
     * @param user user that is unfavouriting this post
     */
    private void unfavourite(Post post, User user){
        post.removeFavouritedUser(user.getId()); // need to update entities to remove error line
        user.removeUserFavourite(post.getID()); // need to update entities to remove error line
    }

}
