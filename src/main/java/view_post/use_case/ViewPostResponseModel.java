package view_post.use_case;

/**
 * A data structure class for storing the output data.
 */
public class ViewPostResponseModel {
    private final String posterEmail;
    private final String postBody;
    private final String postTags;
    private final String deadline;
    private final String creationDate;
    private final String collaborators;
    private final int postID;
    private final String title;

    /**
     * Initializes ViewPostOutputData
     * @param posterEmail A String object that represents the email of the user who created the post
     * @param postBody A String object that represents the main description of the post
     * @param postTags A String array of the tags the post is associated to
     * @param deadline A String object that represents the deadline (expiry date) of the post
     * @param creationDate A String object that represents the creation date of the post
     * @param collaborators A String object that represents the collaborators of the post/project
     * @param postID An Integer that represents the ID of the post
     * @param title A String object that represents the title of the post
     *
     */
    public ViewPostResponseModel(String posterEmail, String postBody, String[] postTags,
                                 String deadline, String creationDate, String collaborators, int postID, String title) {
        this.posterEmail = posterEmail;
        this.postBody = postBody;
        this.postTags = String.join(", ", postTags);
        this.deadline = deadline;
        this.creationDate = creationDate;
        this.collaborators = collaborators;
        this.postID = postID;
        this.title = title;
    }

    /**
     * Return the email of the user who made this post.
     */
    public String getPosterEmail() {
        return posterEmail;
    }

    /**
     * Return the body text of this post.
     */
    public String getPostBody() {
        return postBody;
    }

    /**
     * Return the tags associated with this post.
     */
    public String getPostTags() {
        return postTags;
    }

    /**
     * Return a string representation of the deadline of this post.
     */
    public String getDeadline() {
        return deadline;
    }

    /**
     * Return a string representation of the creation date of this post.
     */
    public String getCreationDate() {
        return creationDate;
    }

    /**
     * Return a string representation of the collaborators of this post.
     */
    public String getCollaborators() {
        return collaborators;
    }

    /**
     * Return the integer representation of the id of the Post.
     */
    public int getPostID() {
        return postID;
    }

    /**
     * Return the string representation of the title of the Post.
     */
    public String getTitle() { return title; }


}
