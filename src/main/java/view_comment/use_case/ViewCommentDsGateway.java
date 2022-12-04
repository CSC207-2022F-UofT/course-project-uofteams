package view_comment.use_case;

import java.util.List;

public interface ViewCommentDsGateway {
    public List<String[]> getAllComments(int postId);
    public List<String[]> getCurrentPosts();
}
