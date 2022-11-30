package view_post.use_case;

import java.util.ArrayList;

/**
 * A data class for storing the output data.
 */
public class ViewPostResponseModel {
    private final String posterEmail;
    private final String postBody;
    private final String postTags;
    private final ArrayList<Integer> postReplies;
    private final String deadline;
    private final String creationDate;
    private final String collaborators;
    private final int postID;
    private final String title;

    /**
     * Initialize a ViewPostResponseModel object.
     * @param posterEmail   The email of the user who made this post.
     * @param postBody      The body text of this post.
     * @param postTags      The associated tags with this post.
     * @param postReplies   The replies made to this post.
     * @param deadline      The string representation of the deadline of this post.
     */
    public ViewPostResponseModel(String posterEmail, String postBody, String[] postTags, String[] postReplies,
                                 String deadline, String creationDate, String collaborators, int postID, String title) {
        this.posterEmail = posterEmail;
        this.postBody = postBody;
        String tags = "";
        for (String tag : postTags){
            tags.concat(", ");
            tags.concat(tag);
        }
        this.postTags = tags;
        ArrayList replies = new ArrayList<>();
        for (String reply : postReplies){
            replies.add(Integer.valueOf(reply));
        }
        this.postReplies = replies;
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
     * Return a list of replies made to this post.
     */
    public ArrayList<Integer> getPostReplies() {
        return postReplies;
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
