package favourite.use_case;

import entities.Post;
import entities.User;

/**
 * The interface implemented by FavouriteDatabaseAccess
 */
public interface FavouriteDsGateway {
    /**
     * Retrieves current user as a User object from the database
     * @param userID the integer ID of the user being retrieved from the database
     * @return the current user as a User object
     */
    User getUser(int userID);

    /**
     * Retrieves the post being favourited/unfavourited as a Post object from the database
     *
     * @param postID the integer ID of the post being favourited/unfavourited
     * @return a Post object
     */
    Post getPostFavourite(int postID);

    /**
     * Rewrites entire csv with the updated User
     * @param updatedUser a String array of the data of the updated user
     * @param userID the integer ID of the updated user
     */
    void saveUserInfo(String[] updatedUser, int userID);

    /**
     * Rewrites entire csv with the updated Post
     * @param updatedPost a String array of the data of the updated post
     * @param postID the integer ID of the updated post
     */
    void savePostInfo(String[] updatedPost, int postID);


}
