package sign_up.use_case;

public interface SignUpDataAccessInterface {
    int getNumberUsers();

    void setNumberUsers(int numUsers);

    boolean checkUserEmailExists(String email);

    void saveUser(Object toSave, String type);

    String getAdminPassword();
}
