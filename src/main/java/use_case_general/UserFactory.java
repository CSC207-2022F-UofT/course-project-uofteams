package use_case_general;

import entities.User;

import java.util.ArrayList;
import java.util.List;

/*
 * User factory class, facilitates making User entities.
 * Has cases for if user is first time new or being remade
 * */

public class UserFactory implements UserReaderInterface{
    /*
    * Create a new User entity based on data passed from client
    * */
    public User create(boolean isAdmin, int numUsersCreated, String email, String password) {
        return new User(isAdmin, numUsersCreated, email, password);
    }


    @Override
    /*
    * Create a new used based on data that is stored in the database
    * */
    public User readUser(String[] userData) {
        // converting string data into acceptable types to reconstruct a User object
        boolean admin = Boolean.parseBoolean(userData[1]);
        int id = Integer.parseInt(userData[0]);
        String email = userData[2];
        String password = userData[3];

        // creating a List of Integers of ids of the user's posts from the datastring
        String[] postids = userData[4].split(" ");
        List<Integer> posts = new ArrayList<>();
        for (String ids: postids){
            posts.add(Integer.parseInt(ids));
        }

        // creating a List of Integers of ids of the user's favourited posts from the datastring
        String[] favids = userData[5].split(" ");
        List<Integer> favourites = new ArrayList<>();
        for (String ids: favids){
            favourites.add(Integer.parseInt(ids));
        }

        // using the variables created above to reconstruct a User object
        return new User(admin, id, email, password, posts, favourites);
    }

}
