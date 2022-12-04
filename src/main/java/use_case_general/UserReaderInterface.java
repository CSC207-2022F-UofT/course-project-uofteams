package use_case_general;

import entities.User;

/*
 * The interface which creates User entities for users that already exist in the database but not
 * in memory
 * */
public interface UserReaderInterface {
    /**
     * Converts csv String data for a Post into a Post Object
     *
     * @param userData Array of Strings, data stored in the database for a user
     * @return a User object with instance variables stored in userData
     */
    User readUser(String[] userData);
}
