package view_post.use_case;

/**
 * A data class for storing the output data.
 */
public class ViewPostResponseModel {
    private final String posterEmail;
    private final String postBody;
    private final String[] postTags;
    private final String[] postReplies;
    private final String deadline;

    /**
     * Initialize a ViewPostResponseModel object.
     * @param posterEmail   The email of the user who made this post.
     * @param postBody      The body text of this post.
     * @param postTags      The associated tags with this post.
     * @param postReplies   The replies made to this post.
     * @param deadline      The string representation of the deadline of this post.
     */
    public ViewPostResponseModel(String posterEmail, String postBody, String[] postTags, String[] postReplies,
                                 String deadline) {
        this.posterEmail = posterEmail;
        this.postBody = postBody;
        this.postTags = postTags;
        this.postReplies = postReplies;
        this.deadline = deadline;
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
    public String[] getPostTags() {
        return postTags;
    }

    /**
     * Return a list of replies made to this post.
     */
    public String[] getPostReplies() {
        return postReplies;
    }

    /**
     * Return a string representation of the deadline of this post.
     */
    public String getDeadline() {
        return deadline;
    }
}
