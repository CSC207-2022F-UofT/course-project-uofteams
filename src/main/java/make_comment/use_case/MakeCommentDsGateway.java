package make_comment.use_case;

import java.util.List;
import java.util.Map;

public interface MakeCommentDsGateway {
    int getNumComments();

    void setNumComments(int newNumCommentCreated);

    void saveComment(Map<String, String> commentAttributes);

    void updatePostDB(List<String[]> updatedPosts);

    List<String[]> getCurrentPosts();


}