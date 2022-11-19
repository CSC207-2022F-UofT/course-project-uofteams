package favourite.use_case;

import entities.User;

import java.util.ArrayList;

public interface UserWriterInterface {
    /**
     * Converts a User Object into a format that can be saved into the database
     *
     * @param user User object being saved into the database
     * @return an ArrayList of the user's instance variables in String form
     */
    ArrayList writeuser(User user);
}
