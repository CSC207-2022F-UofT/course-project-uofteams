package entities;

public class CurrentUser {
    static User currentUser;

    public CurrentUser() {
    }

    public static String getCurrentUser() {
        return currentUser.getEmail();
    }

    public static boolean getIsAdmin() {
        return currentUser.isAdmin();
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }
}

