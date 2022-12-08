package use_case_general;

import entities.Comment;

/*
 * Comment factory class, facilitates making Comment entities.
 * */
public class CommentFactory {
    /**
     * Creates and returns a comment.
     * @param userId the unique identifier of the user that made the comment
     * @param body the text body of the comment
     * @param id the unique identifier of this comment
     */
    public Comment makeComment(int userId, String body, int id) {
        return new Comment(userId, body, id);
    }
}
