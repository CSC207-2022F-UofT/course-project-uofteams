package deadline_timer;

import deadline_timer.use_case.*;
import entities.Post;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entities.User;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

/*
* Initialize dummy dataaccess and output boundary to check that TimerInteractor works properly
* */
public class TimerInteractorTest {
    private static TimerInteractor testInteractor;
    private static DummyDataAccess dummyDataAccess;
    private static DummyOutput dummyOutput;

    private static class DummyDataAccess implements TimerDataAccessInterface {
        private final ArrayList<Post> posts;

        private DummyDataAccess(ArrayList<Post> posts){
            this.posts = posts;
        }
        @Override
        public List<Post> getPosts() {
            return this.posts;
        }

        @Override
        public void delete(Object toDelete) {
            this.posts.remove((Post) toDelete);
        }
    }

    private static class DummyOutput implements TimerOutputBoundary {
        public TimerResponseModel response = new TimerResponseModel(true);

        @Override
        public void present(TimerResponseModel responseModel) {
            this.response.refresh = responseModel.refresh;
        }
    }

    @Before
    public void setup() {
        User poster = new User(false, 0, "email", "password");
        String title = "Title";
        String mainDesc = "Main Description";
        String collaborators = "I would like to play tennis doubles with some fellow beginners";
        List<String> tags = new ArrayList<>(Arrays.asList("Sports", "Club", "Tennis"));

        dummyOutput = new DummyOutput();
        dummyDataAccess = new DummyDataAccess(new ArrayList<>(List.of(new Post(poster, title,
                mainDesc, tags, collaborators, LocalDate.now(), 0))));

        testInteractor = new TimerInteractor(dummyDataAccess, dummyOutput);
    }

    @After
    public void teardown() {}

    @Test
    public void testPostAfterDeadlineDeleted() {
        LocalDate date = LocalDate.of(2022, 11, 13);
        TimerRequestModel testRequest = new TimerRequestModel(date);
        testInteractor.timer(testRequest);

        assertTrue(dummyDataAccess.posts.isEmpty());
    }

    @Test
    public void testPostBeforeDeadlineNotDeleted() {
        LocalDate date = LocalDate.of(2023, 1, 1);
        TimerRequestModel testRequest = new TimerRequestModel(date);
        testInteractor.timer(testRequest);

        assertFalse(dummyDataAccess.posts.isEmpty());
    }

    @Test
    public void testRefreshReturnedIfDeleted() {
        LocalDate date = LocalDate.of(2022, 11, 13);
        TimerRequestModel testRequest = new TimerRequestModel(date);
        testInteractor.timer(testRequest);

        assertTrue(dummyDataAccess.posts.isEmpty());
        assertTrue(dummyOutput.response.refresh);
    }

    @Test
    public void testRefreshNotReturnedIfNotDeleted() {
        LocalDate date = LocalDate.of(2023, 1, 1);
        TimerRequestModel testRequest = new TimerRequestModel(date);
        testInteractor.timer(testRequest);

        assertFalse(dummyDataAccess.posts.isEmpty());
        assertFalse(dummyOutput.response.refresh);
    }

}
