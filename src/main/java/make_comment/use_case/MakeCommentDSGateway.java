package make_comment.use_case;

import java.util.List;
import java.util.Map;

public interface MakeCommentDSGateway {

    /**
     * get the number of posts from the db.
     * @return number of comments from the db.
     */
    int getNumComments();

    /**
     * sets the total number of comments in the database tracking the number of comments.
     */
    void setNumComments(int newNumCommentCreated);

    /**
     * saves the comments into the comment database.
     */
    void saveComment(Map<String, String> commentAttributes);

    /**
     * updates the post that the comment belongs to.
     */
    void updatePostDatabase(List<String[]> updatedPosts);

    /**
     * @return  A list of all posts.
     */
    List<String[]> getCurrentPosts();


}