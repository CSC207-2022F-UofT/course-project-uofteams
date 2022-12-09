package log_in.use_case;

import entities.User;

/**
 * public interface for database access for Log In use case
 */
public interface LogInDsGateway {

    /**
     * Checks if the email exists in the database
     * @param email the email to be checked
     * @return whether the email exists in the database or not as a boolean
     */
    boolean checkUserEmailExists(String email);

    /**
     * Check if a password matches a specific users email
     * @param email the email of the user
     * @param pass the password to be checked if it matches
     * @return a boolean representation of whether the password matches or not
     */
    boolean checkPasswordMatches(String email, String pass);

    /**
     * Get a users information
     * @param success if the logIn check was successful or not
     * @param email a users email
     * @param pass a users password
     * @return a list of Strings containing a users information
     */
    String[] getUser(boolean success, String email, String pass);


    // testing method see LogInTest for usage
    void addUser(User user);
}
