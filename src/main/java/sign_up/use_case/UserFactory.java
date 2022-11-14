package sign_up.use_case;

import entities.User;

public class UserFactory {

    // Create and return a User object
    public User create(boolean isAdmin, int numUsersCreated, String email, String password) {
        return new User(isAdmin, numUsersCreated, email, password);
    }
}
