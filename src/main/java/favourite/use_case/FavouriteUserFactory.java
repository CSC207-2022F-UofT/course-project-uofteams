package favourite.use_case;

import entities.User;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that creates new User objects using data stored in String arrays
 */
public class FavouriteUserFactory implements FavouriteUserReaderInterface {
    /**
     * Creates a new User object based on the data stored in the database
     * @param userData ArrayList of String data stored in the database for a user
     * @return a User object with the qualities described in userdata
     */
    @Override
    public User readUser(String[] userData) {
        // converting string data into acceptable types to reconstruct a User object
        boolean admin = Boolean.parseBoolean(userData[1]);
        int id;
        if (!userData[0].isEmpty()){
            id = Integer.parseInt(userData[0]);
        } else{
            id = -1;
        }
        String email = userData[2];
        String password = userData[3];

        // creating a List of Integers of ids of the user's posts from the datastring
        String[] postIds = userData[4].split(" ");
        List<Integer> posts = new ArrayList<>();
        for (String ids: postIds){
            if (!ids.isEmpty()){
                posts.add(Integer.parseInt(ids));
            }
        }

        // creating a List of Integers of ids of the user's favourited posts from the datastring
        String[] favIds = userData[5].split(" ");
        List<Integer> favourites = new ArrayList<>();
        for (String ids: favIds){
            if (!ids.isEmpty()){
                favourites.add(Integer.parseInt(ids));
            }
        }

        // using the variables created above to reconstruct a User object
        return new User(admin, id, email, password, posts, favourites);
    }
}
