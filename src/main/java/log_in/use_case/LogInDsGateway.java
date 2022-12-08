package log_in.use_case;

import entities.User;

import java.util.ArrayList;

public interface LogInDsGateway {

    // checks if the email exists in the database
    boolean checkUserEmailExists(String email);

    // checks if the password matches an email in the database
    boolean checkPasswordMatches(String email, String pass);

    // gets the user with the given email, password
    User getUser(boolean success, String email);


    // testing method see LogInTest for usage
    void addUser(User user);
}
