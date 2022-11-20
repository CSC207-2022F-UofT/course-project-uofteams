package deadline_timer;

import deadline_timer.interface_adapters.TimerController;
import deadline_timer.use_case.*;
import entities.Post;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import entities.User;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

/*
 * Initialize dummy dataaccess and output boundary to check that TimerInteractor works properly
 * */
public class TimerTest {
    TimerDSGateway postRepository;
    TimerOutputBoundary presenter;
    TimerInputBoundary interactor;
    TimerController controller;

    @Before
    public void setup() {
        postRepository = new TimerDSGateway() {
            private HashMap<Integer, Post> posts;

            @Override
            public HashMap<Integer, Post> getPosts() {
                return this.posts;
            }

            public void addPost(Post post) {
                posts.put(post.getID(), post);
            }
        };
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testPostAfterDeadlineDeleted() {
        presenter = new TimerOutputBoundary() {
            @Override
            public void present(TimerResponseModel responseModel) {}
        };

        interactor = new TimerInteractor(postRepository, presenter);
        controller = new TimerController(interactor);

        LocalDate date = LocalDate.of(2022, 10, 13);

        controller.timer();

        assertTrue(postRepository.getPosts().isEmpty());
    }

    @Test
    public void testPostBeforeDeadlineNotDeleted() {
        presenter = new TimerOutputBoundary() {
            @Override
            public void present(TimerResponseModel responseModel) {}
        };

        interactor = new TimerInteractor(postRepository, presenter);
        controller = new TimerController(interactor);

        LocalDate date = LocalDate.of(2022, 12, 13);

        controller.timer();

        assertFalse(postRepository.getPosts().isEmpty());
    }

    @Test
    public void testRefreshReturnedIfDeleted() {
        presenter = new TimerOutputBoundary() {
            @Override
            public void present(TimerResponseModel responseModel) {
                assertTrue(responseModel.refresh);
            }
        };

        interactor = new TimerInteractor(postRepository, presenter);
        controller = new TimerController(interactor);

        LocalDate date = LocalDate.of(2022, 10, 13);

        controller.timer();
    }

    @Test
    public void testRefreshNotReturnedIfNotDeleted() {
        presenter = new TimerOutputBoundary() {
            @Override
            public void present(TimerResponseModel responseModel) {
                assertFalse(responseModel.refresh);
            }
        };

        interactor = new TimerInteractor(postRepository, presenter);
        controller = new TimerController(interactor);

        LocalDate date = LocalDate.of(2022, 12, 13);

        controller.timer();
    }

}