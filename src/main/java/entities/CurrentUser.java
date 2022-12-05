package entities;

public class CurrentUser {
    private static User currentUser;

    public CurrentUser() {
    }

    public static int getCurrentUser() {
        return currentUser.getId();
    }

    public static boolean getIsAdmin() {
        return currentUser.isAdmin();
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }
}

