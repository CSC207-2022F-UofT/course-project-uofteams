package favourite.use_case;

import entities.User;

import java.util.ArrayList;
import java.util.List;

/**
 * UserFactory in the use case layer implements UserReaderInterface.
 * This class turns data from the database into User entities the FavouriteInteractor can directly interact with.
 */
public class UserFactory implements UserReaderInterface{
    /**
     * Creates a new User object based on the data stored in the database
     * @param userdata ArrayList of String data stored in the database for a user
     * @return User with the qualities described in userdata
     */
    @Override
    public User readUser(String[] userdata) {
        // converting string data into acceptable types to reconstruct a User object
        boolean admin = Boolean.parseBoolean(userdata[1]);
        int id = Integer.parseInt(userdata[0]);
        String email = userdata[2];
        String password = userdata[3];

        // creating a List of Integers of ids of the user's posts from the datastring
        String[] postids = userdata[4].split(" ");
        List<Integer> posts = new ArrayList<>();
        for (String ids: postids){
            posts.add(Integer.parseInt(ids));
        }

        // creating a List of Integers of ids of the user's favourited posts from the datastring
        String[] favids = userdata[5].split(" ");
        List<Integer> favourites = new ArrayList<>();
        for (String ids: favids){
            favourites.add(Integer.parseInt(ids));
        }

        // using the variables created above to reconstruct a User object
        User user = new User(admin, id, email, password, posts, favourites);
        return user;
    }
}
