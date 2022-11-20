package deadline_timer.use_case;

import entities.Post;

import java.util.HashMap;
import java.util.List;

public interface TimerDSGateway {
    public HashMap<Integer, Post> getPosts();
}


