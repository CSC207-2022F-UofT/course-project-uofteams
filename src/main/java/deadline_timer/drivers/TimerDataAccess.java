package deadline_timer.drivers;

import deadline_timer.use_case.TimerDataAccessInterface;
import entities.Post;

import java.util.ArrayList;
import java.util.List;

public class TimerDataAccess implements TimerDataAccessInterface {
    public List<Post> getPosts() {
        return new ArrayList<Post>();
    }
    public void delete(Object toDelete) {

    }
}
