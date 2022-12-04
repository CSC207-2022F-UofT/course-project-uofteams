package view_post.interface_adapters;

/**
 * A data structure to pass the post data retrieved in the DatabaseAccess to the view
 */
public class ViewPostOutputData {
    // email of the user who made the post
    private final String posterEmail;
    // the main description of the post
    private final String postBody;
    // the tags associated with the post
    private final String postTags;
    // the deadline (expiry date) of the post
    private final String deadline;
    // the creation date of the post
    private final String creationDate;
    // the collaborators of the post/project
    private final String collaborators;
    // the ID of the post
    private final int postID;
    // the title of the post
    private final String title;

    /**
     * Initializes ViewPostOutputData
     * @param posterEmail A String object that represents the email of the user who created the post
     * @param postBody A String object that represents the main description of the post
     * @param postTags A String object that lists the tags the post is associated to
     * @param deadline A String object that represents the deadline (expiry date) of the post
     * @param creationDate A String object that represents the creation date of the post
     * @param collaborators A String object that represents the collaborators of the post/project
     * @param postID An Integer that represents the ID of the post
     * @param title A String object that represents the title of the post
     */
    public ViewPostOutputData(String posterEmail, String postBody, String postTags,
                              String deadline, String creationDate, String collaborators, int postID, String title){
        this.posterEmail = posterEmail;
        this.postBody = postBody;
        this.postTags = postTags;
        this.deadline = deadline;
        this.creationDate = creationDate;
        this.collaborators = collaborators;
        this.postID = postID;
        this.title = title;
    }

    /**
     * Returns the email (String) of the user who created the post
     */
    public String getPosterEmail(){
        return this.posterEmail;
    }

    /**
     * Returns the body or main description (String) of the post
     */
    public String getPostBody() {
        return postBody;
    }

    /**
     * Returns the tags (String) the post is affiliated with
     */
    public String getPostTags(){
        return this.postTags;
    }

    /**
     * Returns the deadline/expiry date (String) of the post
     */
    public String getDeadline(){
        return this.deadline;
    }

    /**
     * Returns the creation date (String) of the post
     */
    public String getCreationDate() {
        return creationDate;
    }

    /**
     * Returns the collaborates (String) of the post/project
     */
    public String getCollaborators() {
        return collaborators;
    }

    /**
     * Returns the deadline/expiry date (String) of the post
     */
    public int getPostID() {
        return postID;
    }

    /**
     * Returns the title (String) of the post
     */
    public String getTitle(){return title;}
}


