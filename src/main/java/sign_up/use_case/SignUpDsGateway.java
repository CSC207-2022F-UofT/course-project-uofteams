package sign_up.use_case;

import entities.User;

import java.util.List;

/**
 * The public interface for database access for the sign up use case
 */
public interface SignUpDsGateway {
    /**
     * Return the number of users created in the system
     * @return An int that represents how many users have been created
     */
    int getNumberUsers();

    /**
     * Update the database with the new number of users
     * @param numberUsers The current number of users created
     */
    void setNumberUsers(int numberUsers);

    /**
     * Return all emails in the system
     * @return An ArrayList containing all emails
     * */
    List<String> getEmails();

    /**
     * Save the given User to the database
     * @param toSave the User that is going to be saved
     */
    void saveUser(String[] toSave);

    /**
     * Return the password for the administrator account
     * @return A String representation of the admin password
     */
    String getAdminPassword();
}
