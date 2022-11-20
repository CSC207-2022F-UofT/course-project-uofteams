package deadline_timer.use_case;

import java.util.HashMap;

// Note: this returns object so data access doesnt know about Posts, and also factory and interactor are decoupled
public interface PostReader {
    Object readPost(HashMap<String, Object> postInfo);
}
