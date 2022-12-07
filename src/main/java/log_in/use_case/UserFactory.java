package log_in.use_case;

import entities.User;

/**
 * This will call the constructor to UserFactory to abide by Clean Architecture
 */
public class UserFactory {

    public User create(boolean isAdmin, int numUsersCreated, String email, String password){
        return new User(isAdmin, numUsersCreated, email, password);
    }

}
