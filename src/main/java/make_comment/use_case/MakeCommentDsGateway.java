package make_comment.use_case;

import java.util.List;
import java.util.Map;

public interface MakeCommentDsGateway {

    /**
     * get the number of comments from the db.
     * @return number of comments from the db.
     */
    int getNumComments();

    /**
     * sets the number of comments from the db.
     * @param  newNumCommentCreated new number of comments.
     */
    void setNumComments(int newNumCommentCreated);

    /**
     * saves comment in db.
     * @param  commentAttributes Map representation of Comment.
     */
    void saveComment(Map<String, String> commentAttributes);

    /**
     * saves updated posts in db.
     * @param  updatedPosts Map representation of all posts.
     */
    void updatePostDatabase(List<String[]> updatedPosts);

    /**
     * Get all posts from database.
     * @return all posts.
     */
    List<String[]> getCurrentPosts();


}