package logIn.use_case;

import entities.User;

public interface LogInDsGateway {

    // checks if the email exists in the database
    boolean checkUserEmailExists(String email);

    // checks if the password matches an email in the database
    boolean checkPasswordMatches(String email, String pass);

    // gets the user with the given email, password
    User getUser(boolean success, String email, String pass);


    // testing method see LogInTest for usage
    void addUser(User user);
}
