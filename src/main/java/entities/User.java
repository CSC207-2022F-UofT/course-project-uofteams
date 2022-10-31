package entities;

public class User;
    ArrayList<Post> favourites;
    ArrayList<Post> posts;
    boolean isAdmin;
    String userName;
    String password;

    public User(String name, String pass, boolean admin) {
        this.favourites = ArrayList();
        this.posts = ArrayList();
        this.isAdmin = admin;
        this.userName = name;
        this.password = pass;
        }

    public ArrayList getFavourites(){
        /* Return an ArrayList containing the favourites of this User */
        return this.favourites;
        }

    public void addFavourite(Post toAdd){
        /* Add Post to this.favourites */
        this.favourites.add(Post);
        }

    public boolean removeFavourite(Post toRemove){
        /* Search for Post in this.favourites, remove the first instance found.
        * Return true if found and removed and return false otherwise */
        for (int i; i < this.favourites.size(); i ++){
            if (this.favourites.get(i).equals(toRemove)){
                this.favourites.remove(i);
                return true } }
        return false;
        }

    public boolean checkPassword(String toCheck){
        if this.password.equals(toCheck){
            return true;
        } else{
            return false;
        }
        }

    public ArrayList getPosts() {
        /* return an ArrayList containing the elements of this.posts */
        return this.posts;
        }

    public void addPost(Post toAdd){
        /* add toAdd to this.posts */
        this.posts.add(toAdd);
        }

    public boolean removePost(Post toRemove){
        /* Search for toRemove in this.posts and if it is the remove and return true,
        * otherwise return false */
        for (int i; i < this.posts.size(); i ++){
            if (this.posts.get(i).equals(toRemove)){
                this.posts.remove(i);
                return true;
        }}
        return false;
        }