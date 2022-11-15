package entities;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;

/**
 * The Post class contains all the necessary information of a post: its ID, title,
 * description,tags,collaborators, deadline, and users who favourited it.
 */
public class Post extends Postable{
    public static int numPostsCreated = 0;
    /**The id is equal to the number of posts created.*/
    private int id;
    /**This is a list of the users who favourited this post.*/
    private List<User> favouritedUsers;
    private String title;
    private List<String> tags;
    private String collaborators;
    private LocalDate deadline;

    /**
     * @param poster the User who is posting this Post. This is an attribute of the superclass Postable.
     * @param title this Post's title.
     * @param mainDesc this Post's description.
     * @param tags the tags this Post is added to.
     * @param collaborators a description of the type of the collaborators the User who posted this Posts is looking for.
     * @param deadline the date on which the Post is to be deleted from the database.
     * @param numPostsCreated1 the number of posts created so far.
     */
    public Post(User poster, String title, String mainDesc, List<String> tags, String collaborators,
                LocalDate deadline, int numPostsCreated1){
        super.user = poster;
        super.body = mainDesc;
        super.replies = new ArrayList<>();
        super.creationDate = LocalDate.now();
        numPostsCreated = numPostsCreated1;
        numPostsCreated++;
        this.id = numPostsCreated;
        this.title = title;
        this.tags = tags;
        this.collaborators = collaborators;
        this.deadline = deadline;
        this.favouritedUsers = new ArrayList<>();
    }

    /**
     * @return the Post's ID.
     */
    public int getID(){
        return this.id;
    }

    /**
     * @return a list of the Users who favourited the Post.
     */
    public List<User> getFavouritedUsers(){
        return this.favouritedUsers;
    }

    /**
     * @return a list of tags specified by the User who made the Post.
     */
    public List<String> getTags(){
        return this.tags;
    }

    /**
     *
     * @return the number of Users who favourited the Post.
     */
    public int getNumFavouritedUsers(){
        return this.favouritedUsers.size();
    }

    /**
     * @return this post's deadline.
     */
    public LocalDate getDeadline(){
        return this.deadline;
    }

    /**
     * @return the replies made to this Post.
     */
    public List<Postable> getReplies(){
        return this.replies;
    }
    /**
     * adds a reply to the list of replies to this Post.
     * @param reply a reply to this Post.
     * @return true if reply has been added; false otherwise.
     */
    public boolean addReply(Comment reply){
        if(!(this.replies.contains(reply))){
            this.replies.add(reply);
            return true;
        }
        return false;
    }

    /**
     * Searches for reply in the list of replies to the Post, removes that reply if
     * that it is there.
     * @param reply the reply to be removed.
     * @return true if the reply has been removed; false otherwise.
     */
    public boolean removeReply(Comment reply){
        for(int i = 0; i < replies.size(); i++){
            if(reply.equals(replies.get(i))){
                replies.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * adds a User to the list of Users who favourited the Post if it is not in favouritedUsers yet.
     * @param favouritedUser the User who favourited the Post.
     * @return true if user has been added; false otherwise.
     */
    public boolean addFavouritedUser(User favouritedUser){
        if(!(favouritedUsers.contains(favouritedUser))){
            this.favouritedUsers.add(favouritedUser);
            return true;
        }
        return false;
    }

    /**
     * Searches for userToRemove in the list of Users who favourited the Post, removes userToRemove if
     * that user is there.
     * @param userToRemove the User to be removed.
     * @return true if the user has been removed; false otherwise.
     */
    public boolean removeFavouritedUser(User userToRemove){
        for(int i = 0; i < favouritedUsers.size(); i++){
            if(userToRemove.equals(favouritedUsers.get(i))){
                favouritedUsers.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * set the tags that this Post is categorised as by the Poster.
     * @param tags the tags this Post is categorised as by the Poster.
     */
    public void setTags(List<String> tags){
        this.tags = tags;
    }

    /**
     * Checks whether two posts are equal based on whether their Post id's are equal.
     * @param o the Post to be compared to.
     * @return a boolean that reflects whether the two Posts' id's are equal.
     */
    @Override
    public boolean equals(Object o){
        if (o == null){
            return false;
        }
        if (!(o instanceof Post)) {
            return false;
        }

        Post other = (Post) o;
        return this.id == other.id;
    }
}

