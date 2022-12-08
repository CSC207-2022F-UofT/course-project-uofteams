package entities;
import java.util.ArrayList;
import java.util.List;

public class User {
    // ArrayList of User's favourited posts
    private final List<Integer> favourites;

    // ArrayList of Posts User has made
    private final List<Integer> posts;

    // boolean relating if User has admin privileges
    private final boolean isAdmin;

    // id is the unique identifier of User, equal to number of posts at time of creation
    private final int id;

    // password is the user's input password, used to sign in
    private final String password;

    // email is the users input email, used to sign in
    private final String email;

    /*
    * Initializes an instance of User
    *
    * @param admin whether the user will have admin priviledges
    * */
    public User(boolean admin, int id, String email, String password) {
        this.favourites = new ArrayList<>();
        this.posts = new ArrayList<>();
        this.isAdmin = admin;
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public User(boolean admin, int id, String email, String password, List<Integer> posts, List<Integer> favourites){
        this.isAdmin = admin;
        this.id = id;
        this.email = email;
        this.password = password;
        this.posts = posts;
        this.favourites = favourites;
    }

    /*
    * Returns User's favourites
    *
    * @return User's favourites attribute
    * */
    public List<Integer> getFavourites() {
        /* Return an ArrayList containing the favourites of this User */
        return this.favourites;
    }

    /*
    * Adds a Post to User's favourites
    *
    * @param toAdd The Post to add to User's favourites
    * */
    public void addFavourite(int toAdd) {
        /* Add Post to this.favourites */
        this.favourites.add(toAdd);
    }

    /*
    * Check if a Post is in User's favourites and remove it if so
    *
    * @param toRemove Post to be removed
    * @return boolean representing success or failure
    * */
    public void removeUserFavourite(int toRemove) {
        for (int i = 0; i < this.favourites.size(); i++) {
            if (this.favourites.get(i).equals(toRemove)) {
                this.favourites.remove(i);
                break;
            }
        }
    }

    /*
    * Return Posts made by User
    *
    * @return User's posts attribute
    * */
    public List<Integer> getPosts() {
        /* return an ArrayList containing the elements of this.posts */
        return this.posts;
    }

    /*
    * Checks whether User has admin privileges
    *
    * @return a boolean which represents whether User has admin privileges
    * */
    public boolean isAdmin(){
        return this.isAdmin;
    }

    /*
    * Return if User is equal to o
    *
    * @param o The Object to be compared to
    * @return boolean representing whether User is equal to o
    * */
    @Override
    public boolean equals (Object o) {
        if (o == null) {
            return false;
        } else if (!(o instanceof User)) {
            return false;
        }  else {
            return (this.id == ((User) o).id);
        }
    }

    /*
    * Return the id of this user
    *
    * @return user's id int
    * */
    public int getId() {
        return this.id;
    }

    /*
    * Returns the users email
    *
    * @return a String with the value of user email
    * */
    public String getEmail(){
        return this.email;
    }

    /*
    * Returns the users password
    *
    * @return a String with the value of User's password
    * */
    public String getPassword() {
        return this.password;
    }
}
