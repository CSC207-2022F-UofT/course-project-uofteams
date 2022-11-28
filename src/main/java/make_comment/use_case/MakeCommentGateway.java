package make_comment.use_case;

import java.util.Map;

public interface MakeCommentGateway {
    int getNumComments();

    int getCurrentUserid();

    void setNumComments(int newNumCommentCreated);

    void saveComment(Map<String, String> commentAttributes);

    void updatePostDB();

    Map<String, String> getCurrentUser();

    int getCurrentPostID();

}