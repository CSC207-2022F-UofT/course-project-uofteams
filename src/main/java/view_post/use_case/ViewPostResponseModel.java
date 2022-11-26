package view_post.use_case;

import java.util.ArrayList;
import java.util.List;

/**
 * A data class for storing the output data.
 */
public class ViewPostResponseModel {
    private final String posterEmail;
    private final String postBody;
    private final String postTags;
    private final List<Integer> postReplies;
    private final String deadline;
    private final int postID;

    /**
     * Initialize a ViewPostResponseModel object.
     *
     * @param postID      The ID of the post that the user wants to display.
     * @param posterEmail The email of the user who made this post.
     * @param postBody    The body text of this post.
     * @param postTags    The associated tags with this post.
     * @param postReplies The replies made to this post.
     * @param deadline    The string representation of the deadline of this post.
     */
    public ViewPostResponseModel(int postID, String posterEmail, String postBody, String postTags, String[] postReplies,
                                 String deadline) {
        this.posterEmail = posterEmail;
        this.postBody = postBody;
        this.postTags = postTags;
        this.postReplies = new ArrayList<>();

        for (String reply: postReplies) {
            int replyID = Integer.parseInt(reply);
            this.postReplies.add(replyID);
        }

        this.deadline = deadline;
        this.postID = postID;
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
    public List<Integer> getPostReplies() {
        return postReplies;
    }

    /**
     * Return a string representation of the deadline of this post.
     */
    public String getDeadline() {
        return deadline;
    }

    /**
     * Return the id of this post.
     */
    public int getPostID() {
        return postID;
    }
}
