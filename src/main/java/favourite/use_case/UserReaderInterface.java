package favourite.use_case;

import entities.User;

public interface UserReaderInterface {
    /**
     * Converts csv String data for a User into a User Object
     *
     * @param userdata ArrayList of String data stored in the database for a user
     * @return a User object with instance variables stored in userdata
     */
    User readUser(String[] userdata);
}
