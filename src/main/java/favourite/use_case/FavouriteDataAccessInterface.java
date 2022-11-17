package favourite.use_case;

import entities.Post;
import entities.User;

public interface FavouriteDataAccessInterface {
    /**
     * Checks whether this user has already added to this user's favourites
     *
     * @param post post that this user is trying to favourite
     * @param user user that is trying to favourite this post
     * @return true if this user has already favourited this post, false otherwise
     */
    public boolean checkIfFavourited(Post post, User user);

    /**
     * Returns the User who is trying to favourite this post
     */
    public User getUser();

    /**
     * Returns the Post this user is trying to favourite
     */
    public Post getPost();

}
