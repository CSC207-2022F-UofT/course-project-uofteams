package entities;

public class CurrentUser {
    static User currentUser;

    public CurrentUser() {
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static boolean getIsAdmin() {
        return currentUser.isAdmin();
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }
}

