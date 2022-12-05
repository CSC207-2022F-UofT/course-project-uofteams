package view_comment.use_case;

import java.util.List;

public interface ViewCommentDsGateway {
    public List<String[]> getAllComments();
    public List<String[]> getAllPosts();
}
