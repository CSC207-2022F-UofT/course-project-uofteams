package entities;

public class CurrentUser {
    /**
     * Class containing User currently logged in to program
     */
    private static User currentUser;

    /**
     * Initialize instance of CurrentUser object
     */
    public CurrentUser() {
    }

    /**
     * Get User object of user currently logged in
     * @return User object currently logged in
     */
    public static User getCurrentUser() {
        return currentUser;
    }

    /**
     * Get ID of user currently logged in
     * @return ID of user currently logged in
     */
    public static int getCurrentUserId() {
        return currentUser.getId();
    }

    /**
     * Checks whether current user is an admin
     * @return true if current user is an admin, otherwise false
     */
    public static boolean getIsAdmin() {
        return currentUser.isAdmin();
    }

    /**
     * Sets user currently logged in
     * @param user User object currently logged in
     */
    public static void setCurrentUser(User user) {
        currentUser = user;
    }
}

