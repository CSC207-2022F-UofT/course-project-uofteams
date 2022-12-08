package view_comment.use_case;

import java.util.List;

public interface ViewCommentDsGateway {

    /**
     * Return a list of strings representing the all comments.
     */
    List<String[]> getAllComments();

    /**
     * Return a list of strings representing the all posts.
     */
    List<String[]> getAllPosts();
}