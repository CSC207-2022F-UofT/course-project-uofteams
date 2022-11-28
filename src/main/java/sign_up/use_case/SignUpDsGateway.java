package sign_up.use_case;

import entities.User;

import java.util.List;

public interface SignUpDsGateway {
    int getNumberUsers();

    void setNumberUsers(int numberUsers);

    List<String> getEmails();

    void saveUser(User toSave);

    String getAdminPassword();
}
