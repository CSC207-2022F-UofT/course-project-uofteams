package deadline_timer.use_case;

import entities.Post;
import java.util.ArrayList;
import java.util.List;

public interface TimerDataAccessInterface {
    public List<Post> getPosts();
    public void delete(Object toDelete);
}


