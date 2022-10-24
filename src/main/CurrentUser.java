public class CurrentUser {
    static String currentUser;
    static boolean isAdmin = false;

    public CurrentUser(){}

    public static String getCurrentUser(){
        return currentUser;
    }
    public static boolean getIsAdmin(){
        return isAdmin;
    }
    public static void setCurrentUser(String user){
        currentUser = user;
    }
    public static void setIsAdmin(boolean admin){
        isAdmin = admin;
    }
}

