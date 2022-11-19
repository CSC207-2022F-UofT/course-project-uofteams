package favourite.use_case;

import entities.User;

import java.util.ArrayList;

/**
 * UserStringFactory in the use case layer implements UserWriterInterface.
 * It converts User objects into a format that the database can save.
 */
public class UserStringFactory implements UserWriterInterface{
    @Override
    public ArrayList writeuser(User user){
        ArrayList userdata = new ArrayList();
        userdata.add(user.getId());
        userdata.add(user.getEmail());
        userdata.add(user.getPassword());
        userdata.add(user.getPosts()); // this is a list, needs to turn into string
        userdata.add(user.getFavourites()); // this is a list, needs to turn into string
        return userdata;
    }
}
