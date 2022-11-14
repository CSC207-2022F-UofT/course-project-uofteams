package sign_up.drivers;

import sign_up.use_case.SignUpDataAccessInterface;

public class DatabaseAccess implements SignUpDataAccessInterface {
    @Override
    public int getNumberUsers() {
        return 0;
    }

    @Override
    public void setNumberUsers(int numUsers) {

    }

    @Override
    public boolean checkUserEmailExists(String email) {
        return false;
    }

    @Override
    public void saveUser(Object toSave, String type) {

    }

    @Override
    public String getAdminPassword() {
        return null;
    }
}
