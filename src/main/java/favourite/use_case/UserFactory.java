package favourite.use_case;

import entities.User;

import java.util.ArrayList;

/**
 * UserFactory in the use case layer implements UserReaderInterface.
 * This class turns data from the database into User entities the FavouriteInteractor can directly interact with.
 */
public class UserFactory implements UserReaderInterface{
    @Override
    public User readUser(ArrayList userdata) {
        // use new User constructor to recreate user from this.userdata
        return null;
    }
}
