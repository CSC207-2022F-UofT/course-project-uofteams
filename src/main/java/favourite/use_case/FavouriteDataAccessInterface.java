package favourite.use_case;

import entities.Post;
import entities.User;

public interface FavouriteDataAccessInterface {
    /**
     * Returns the User that is trying to favourite this post
     */
    User getUser();

    /**
     * Returns the Post the user is trying to favourite
     *
     * @param postid id of post being searched for
     */
    Post getPost(int postid);

    /**
     * Saves the edited User in the database
     *
     * @param user User being saved
     */
    void saveUserInfo(User user);

    /**
     * Saves the edited Post in the database
     *
     * @param post Post being saved
     * @param postid id of the Post being saved
     */
    void savePostInfo(Post post, int postid);


}
