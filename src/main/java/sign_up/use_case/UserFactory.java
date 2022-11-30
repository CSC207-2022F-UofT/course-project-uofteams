package sign_up.use_case;

import entities.User;

// UserFactory is responsible for calling the constructor for the USer entity
public class UserFactory {

    // Create and return a User object that has not been created before
    public User create(boolean isAdmin, int numUsersCreated, String email, String password) {
        return new User(isAdmin, numUsersCreated, email, password);
    }
}
