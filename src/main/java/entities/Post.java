package entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The Post class contains all the necessary information of a post: its ID, title,
 * description,tags,collaborators, deadline, and users who favourited it.
 */
public class Post extends Postable{
    private String title;
    private List<String> tags;
    private String collaborators;
    private LocalDate deadline;
    /**This is a list of the users who favourited this post.*/
    private List<Integer> favouritedUsersIDs;
    // a list of Postable objects created as a reply to this Postable
    private List<Integer> repliesIDs;

    /**
     * This constructor makes a new post that does not yet exist in the database.
     * @param posterID the ID of the User who is posting this Post. This is an attribute of the superclass Postable.
     * @param title this Post's title.
     * @param mainDesc this Post's description.
     * @param tags the tags this Post is added to.
     * @param collaborators a description of the type of the collaborators the User who posted this Posts is looking for.
     * @param deadline the date on which the Post is to be deleted from the database.
     * @param id  unique identifier for this post
     */
    public Post(int posterID, String title, String mainDesc, List<String> tags, String collaborators,
                LocalDate deadline, int id){
        super.userID = posterID;
        super.body = mainDesc;
        super.id = id;
        super.creationDate = LocalDate.now();
        this.repliesIDs = new ArrayList<>();
        this.title = title;
        this.tags = tags;
        this.collaborators = collaborators;
        this.deadline = deadline;
        this.favouritedUsersIDs = new ArrayList<>();
    }

    /**
     * This is the constructor makes a post that already exists in the database and that is being manipulated
     * and then re-uploaded into the db. Most of the parameters are exactly the same as above.
     * @param posterID refer to above.
     * @param title refer to above.
     * @param mainDesc refer to above.
     * @param tags refer to above.
     * @param collaborators refer to above.
     * @param deadline refer to above.
     * @param creationDate a post from the database already has a creationDate.
     * @param id a post from the database already has an id that will not be updated.
     * @param favouritedUsersIDs a post from the db may already have users who favourited it.
     * @param repliesIDs a post from the db may already have replies.
     */
    public Post(int posterID, String title, String mainDesc, List<String> tags, String collaborators, LocalDate deadline,
                LocalDate creationDate, int id, List<Integer> favouritedUsersIDs, List<Integer> repliesIDs){
        super.userID = posterID;
        super.body = mainDesc;
        this.repliesIDs = repliesIDs;
        super.creationDate = creationDate;
        this.id = id;
        this.title = title;
        this.tags = tags;
        this.collaborators = collaborators;
        this.deadline = deadline;
        this.favouritedUsersIDs = favouritedUsersIDs;
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
    public List<Integer> getFavouritedUsers(){
        return this.favouritedUsersIDs;
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
        return this.favouritedUsersIDs.size();
    }

    /**
     * @return this post's deadline.
     */
    public LocalDate getDeadline(){
        return this.deadline;
    }

    /**
     * Returns the list of replies to a Postable object.
     */
    public List<Integer> getReplies(){return this.repliesIDs;}

    /**
     * adds a reply to the list of replies to this Post.
     * @param reply a reply to this Post.
     * @return true if reply has been added; false otherwise.
     */
    public boolean addReply(int reply){
        if(!(this.repliesIDs.contains(reply))){
            this.repliesIDs.add(reply);
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
    public boolean removeReply(int reply){
        for(int i = 0; i < repliesIDs.size(); i++){
            if(reply == repliesIDs.get(i)){
                repliesIDs.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Return the post's title.
     */
    public String getTitle() {
        return this.title;
    }


    /**
     * adds a User to the list of Users who favourited the Post if it is not in favouritedUsers yet.
     * @param favouritedUser the User who favourited the Post.
     * @return true if user has been added; false otherwise.
     */
    public boolean addFavouritedUser(int favouritedUser){
        if(!(favouritedUsersIDs.contains(favouritedUser))){
            this.favouritedUsersIDs.add(favouritedUser);
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
    public boolean removeFavouritedUser(int userToRemove){
        for(int i = 0; i < favouritedUsersIDs.size(); i++){
            if(userToRemove == favouritedUsersIDs.get(i)){
                favouritedUsersIDs.remove(i);
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
     * Returns the collaborators of this post
     * @return a string with the collaborator information
     */
    public String getCollaborators(){return this.collaborators;}

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

