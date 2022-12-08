package log_in.use_case;


import java.util.ArrayList;


public interface LogInDsGateway {

    // checks if the email exists in the database
    boolean checkUserEmailExists(String email);

    // checks if the password matches an email in the database
    boolean checkPasswordMatches(String email, String pass);

    // gets the user with the given email, password
    String[] getUser(boolean success, String email, String pass);

}
