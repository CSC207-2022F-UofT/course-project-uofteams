package favourite.use_case;

import entities.Post;
import entities.User;

public interface FavouriteDSGateway {
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
     * @param updateduser data of the User being saved
     * @param userid id of the User being saved
     */
    void saveUserInfo(String[] updateduser, int userid);

    /**
     * Saves the edited Post in the database
     *
     * @param updatedpost data of the Post being saved
     * @param postid id of the Post being saved
     */
    void savePostInfo(String[] updatedpost, int postid);


}
