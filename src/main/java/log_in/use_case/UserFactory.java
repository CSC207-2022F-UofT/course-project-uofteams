package log_in.use_case;

import entities.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This will call the constructor to UserFactory to abide by Clean Architecture
 */
public class UserFactory {


    /**
     * Intialize an UserFactory to create a user
     * @param isAdmin if a user an admin or not
     * @param numUsersCreated the number of users created
     * @param email the users email
     * @param password the users password
     * @param posts the number of posts the user had
     * @param favourites the number of favourite posts the user had
     * @return a new user object with all given parameters
     */
    public User create(boolean isAdmin, int numUsersCreated, String email, String password,
                       List<Integer> posts, List<Integer> favourites){
        return new User(isAdmin, numUsersCreated, email, password, posts, favourites);
    }


    // converts a list of string to a list of int
    public List<Integer> convertPost(String convert){
        String[] convertedArray = convert.split(",");
        List<Integer> convertedList = new ArrayList<Integer>();
        for (String number : convertedArray) {
            convertedList.add(Integer.parseInt(number.trim()));
        }
        return convertedList;
    }


}
