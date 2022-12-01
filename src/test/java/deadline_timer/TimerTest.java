package deadline_timer;

import deadline_timer.interface_adapters.TimerController;
import deadline_timer.use_case.*;
import delete_post.use_case.DeletePostInputBoundary;
import delete_post.use_case.DeletePostRequestModel;
import entities.Post;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

/*
 * Initialize dummy dataaccess and output boundary to check that TimerInteractor works properly
 * */
public class TimerTest {
    public PostRepo postRepository;
    public TimerInputBoundary interactor;
    public TimerController controller;
    public DeletePostInputBoundary testBoundary;

    public class PostRepo implements TimerDSGateway {
        public HashMap<Integer, Post> posts;
        @Override
        public HashMap<Integer, Post> getPosts() {
            return this.posts;
        }

        public void addPost(Post post) {
            posts.put(post.getID(), post);
        }
    }
    @Before
    public void setup() {
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testPostAfterDeadlineDeleted() {
        this.postRepository = new PostRepo();
        this.testBoundary = new DeletePostInputBoundary() {
            @Override
            public void delete(DeletePostRequestModel requestModel) {

            }
        };
        this.postRepository.addPost(new Post(1, "hi", "hi", new ArrayList<>(),
                "collab", LocalDate.of(2022, 10, 13), 0));
        interactor = new TimerInteractor(postRepository, testBoundary);
        controller = new TimerController(interactor);

        controller.timer();

        assertTrue(postRepository.getPosts().isEmpty());
    }

    @Test
    public void testPostBeforeDeadlineNotDeleted() {
        this.postRepository = new PostRepo();
        this.testBoundary = new DeletePostInputBoundary() {
            @Override
            public void delete(DeletePostRequestModel requestModel) {}
        };

        this.postRepository.addPost(new Post(1, "hi", "hi", new ArrayList<>(),
                "collab", LocalDate.of(2022, 12, 13), 0));

        interactor = new TimerInteractor(postRepository, testBoundary);
        controller = new TimerController(interactor);

        controller.timer();

        assertFalse(postRepository.getPosts().isEmpty());
    }

    @Test
    public void testIsTimerTrue() {
        this.postRepository = new PostRepo();
        this.testBoundary = new DeletePostInputBoundary() {
            @Override
            public void delete(DeletePostRequestModel requestModel) {
                assertTrue(requestModel.getIsTimer());
            }
        };
        this.postRepository.addPost(new Post(1, "hi", "hi", new ArrayList<>(),
                "collab", LocalDate.of(2022, 10, 13), 0));

        interactor = new TimerInteractor(postRepository, testBoundary);
        controller = new TimerController(interactor);

        controller.timer();

    }

}