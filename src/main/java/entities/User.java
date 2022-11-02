package entities;

public class User {
    // ArrayList of User's favourited posts
    private ArrayList<Post> favourites;

    // ArrayList of Posts User has made
    private ArrayList<Post> posts;

    // boolean relating if User has admin privileges
    private boolean isAdmin;

    // Number of Users created
    public static int numUsers = 0;

    // id is the unique identifier of User, equal to number of posts at time of creation
    private int id;

    /*
    * Initializes an instance of User
    *
    * @param admin whether the user will have admin priviledges
    * */
    public User(boolean admin, int numUsersCreated) {
        this.favourites = new ArrayList<Post>();
        this.posts = new ArrayList<Post>();
        this.isAdmin = admin;
        User.numUsers += numUsersCreated;
        this.id = User.numUsers;
    }

    /*
    * Returns User's favourites
    *
    * @return User's favourites attribute
    * */
    public ArrayList getFavourites() {
        /* Return an ArrayList containing the favourites of this User */
        return this.favourites;
    }

    /*
    * Adds a Post to User's favourites
    *
    * @param toAdd The Post to add to User's favourites
    * */
    public void addFavourite(Post toAdd) {
        /* Add Post to this.favourites */
        this.favourites.add(toAdd);
    }

    /*
    * Check if a Post is in User's favourites and remove it if so
    *
    * @param toRemove Post to be removed
    * @return boolean representing success or failure
    * */
    public boolean removeFavourite(Post toRemove) {
        for (int i = 0; i < this.favourites.size(); i++) {
            if (this.favourites.get(i).equals(toRemove)) {
                this.favourites.remove(i);
                return true
            }
        }
        return false;
    }

    /*
    * Return Posts made by User
    *
    * @return User's posts attribute
    * */
    public ArrayList getPosts() {
        /* return an ArrayList containing the elements of this.posts */
        return this.posts;
    }

    /*
    * Add a Post to User's posts
    *
    * @param toAdd post to be added
    * */
    public void addPost(Post toAdd) {
        /* add toAdd to this.posts */
        this.posts.add(toAdd);
    }

    /*
    * Check if a Post is in User's posts and remove it if so
    *
    * @param toRemove post to be removed
    * @returns a boolean representing success or failure
    * */
    public boolean removePost(Post toRemove) {
        /* Search for toRemove in this.posts and if it is the remove and return true,
         * otherwise return false */
        for (int i = 0; i < this.posts.size(); i++) {
            if (this.posts.get(i).equals(toRemove)) {
                this.posts.remove(i);
                return true;
            }
        }
        return false;
    }

    /*
    * Checks whether User has admin privledges
    *
    * @return a boolean which represents whether User has admin priviledges
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
        if (this == o) {
            return true;
        } else if (o == null) {
            return false;
        } else if (!(o instanceof User)) {
            return false;
        }  else {
            return (this.id == ((User) o).id);
        }
    }
}