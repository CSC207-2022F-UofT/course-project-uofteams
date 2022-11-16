package filter_post;

import entities.Post;
import entities.User;
import filter_post.interface_adapters.FilterPostController;
import filter_post.use_case.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test the functionality of the "filter post" use case.
 *
 * Included Test Coverage:
 *  - All of filter_pose.use_case (100% coverage)
 *  - filter_post.interface_adapters.FilterPostController (100% coverage)
 *
 *  The test coverage did not include the rest of the components because they were UI-related and I don't
 *  know how to test them. Future work would include implementing tests for these components.
 */
public class FilterPostTest {
    FilterPostRequestModel requestModel;
    FilterPostResponseModel responseModel;
    FilterPostDsGateway postRepository;
    FilterPostOutputBoundary presenter;
    FilterPostInputBoundary interactor;
    FilterPostController controller;

    @Before
    public void setUp() {
        // Create an anonymous class to mimic the data access class.
        postRepository = new FilterPostDsGateway() {
            @Override
            public List<Post> getPosts() {
                List<Post> dummyPosts = new ArrayList<>();
                User p = new User(false, 0, "test@email.com", "moogah");
                LocalDate d = LocalDate.of(2022, 12, 31);

                for (int i = 0; i < 5; i++) {
                    List<String> tags = new ArrayList<>();
                    tags.add(String.valueOf(i));
                    Post temp = new Post(p, "Test " + i, "Testing " + i, tags, "", d, i);
                    dummyPosts.add(temp);
                }

                return dummyPosts;
            }
        };

        requestModel = new FilterPostRequestModel();
        responseModel = new FilterPostResponseModel();
    }

    @After
    public void tearDown() {}

    /**
     * Test that the FilterPost use case correctly updates the viewable posts with a non-empty
     * input to the controller.
     */
    @Test
    public void testFilterNonEmptyTags() {
        // Create an anonymous class to act as the presenter
        presenter = new FilterPostOutputBoundary() {
            List<Post> viewablePosts;

            @Override
            public void updateViewablePosts(FilterPostResponseModel filteredPosts) {
                viewablePosts = filteredPosts.getFilteredPosts();
                Post p = viewablePosts.get(0);

                assertEquals(1, viewablePosts.size());
                assertEquals(4, p.getID());
                assertEquals("3", p.getTags().get(0));
            }
        };

        interactor = new FilterPostInteractor(responseModel, postRepository, presenter);
        controller = new FilterPostController(requestModel, interactor);

        List<String> inputData = new ArrayList<>();
        inputData.add("3");

        controller.filter(inputData);
    }

    /**
     * Test that the FilterPost use case correctly updates the viewable posts with an empty
     * input to the controller.
     */
    @Test
    public void testFilterEmptyTags() {
        // Create an anonymous class to act as the presenter
        presenter = new FilterPostOutputBoundary() {
            List<Post> viewablePosts;

            @Override
            public void updateViewablePosts(FilterPostResponseModel filteredPosts) {
                viewablePosts = filteredPosts.getFilteredPosts();

                assertEquals(5, viewablePosts.size());

                for (int i = 0; i < 5; i++) {
                    Post p = viewablePosts.get(i);

                    assertEquals(i + 1, p.getID());
                    assertEquals(String.valueOf(i), p.getTags().get(0));
                }
            }
        };

        interactor = new FilterPostInteractor(responseModel, postRepository, presenter);
        controller = new FilterPostController(requestModel, interactor);

        List<String> inputData = new ArrayList<>();

        controller.filter(inputData);
    }
}