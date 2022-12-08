package favourite.use_case;

import entities.User;

/**
 * An interface for UserFactory
 */
public interface FavouriteUserReaderInterface {
    /**
     * Creates a new User object based on the data stored in the database
     * @param userData ArrayList of String data stored in the database for a user
     * @return a User object with the qualities described in userdata
     */
    User readUser(String[] userData);
}
