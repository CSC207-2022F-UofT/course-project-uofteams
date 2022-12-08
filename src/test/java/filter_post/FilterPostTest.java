package filter_post;

import filter_post.drivers.FilterPostDataAccess;
import filter_post.interface_adapters.FilterPostController;
import filter_post.interface_adapters.FilterPostPresenter;
import filter_post.interface_adapters.FilterPostViewModel;
import filter_post.use_case.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.beans.PropertyChangeListener;

import static org.junit.Assert.*;

/**
 * Test the functionality of the "filter post" use case.
 * Included Test Coverage:
 *  - filter_pose.use_case (100% line coverage)
 *  - filter_post.interface_adapters (92% line coverage)
 *  - filter_post.drivers.FilterPostDataAccess (100% coverage)
 * Notes:
 *  - The test coverage did not include the rest of the components because they were UI-related and I don't
 *    know how to test them. Future work would include implementing tests for these components.
 *  - I did not test the getters of FilterPostViewModel since there is not exactly an important behavioural
 *    concept to test (they simply just return a value).
 */
public class FilterPostTest {
    FilterPostDsGateway postRepository;
    FilterPostOutputBoundary presenter;
    FilterPostInputBoundary interactor;
    FilterPostController controller;

    @Before
    public void setUp() {
        String path = "src/test/java/filter_post/posts.csv";
        postRepository = new FilterPostDataAccess(path);
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
            String[][] viewablePosts;

            @Override
            public void updateViewablePosts(FilterPostResponseModel filteredPosts) {
                viewablePosts = filteredPosts.getFilteredPosts();
                String[] post = viewablePosts[0];
                int postID = Integer.parseInt(post[0]);
                String postTitle = post[1];
                String postDescription = post[2];

                assertEquals(1, viewablePosts.length);
                assertEquals(3, postID);
                assertEquals("Test 3", postTitle);
                assertEquals("Testing 3", postDescription);
            }
        };

        interactor = new FilterPostInteractor(postRepository, presenter);
        controller = new FilterPostController(interactor);

        String[] inputData = {"3"};

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
            String[][] viewablePosts;

            @Override
            public void updateViewablePosts(FilterPostResponseModel filteredPosts) {
                viewablePosts = filteredPosts.getFilteredPosts();

                assertEquals(5, viewablePosts.length);

                for (int i = 0; i < 5; i++) {
                    String[] post = viewablePosts[i];
                    int postID = Integer.parseInt(post[0]);
                    String postTitle = post[1];
                    String postDescription = post[2];

                    assertEquals(i, postID);
                    assertEquals("Test " + i, postTitle);
                    assertEquals("Testing " + i, postDescription);
                }
            }
        };

        interactor = new FilterPostInteractor(postRepository, presenter);
        controller = new FilterPostController(interactor);

        String[] inputData = {};

        controller.filter(inputData);
    }

    /**
     * Test that the use case correctly returns no viewable posts when there are no posts.
     */
    @Test
    public void testFilterNoPosts() {
        String path = "src/test/java/filter_post/empty.csv";
        postRepository = new FilterPostDataAccess(path);
        // Create an anonymous class to act as the presenter.
        presenter = filteredPosts -> {
            String[][] viewablePosts;
            viewablePosts = filteredPosts.getFilteredPosts();

            assertEquals(0, viewablePosts.length);
        };

        interactor = new FilterPostInteractor(postRepository, presenter);
        controller = new FilterPostController(interactor);

        String[] inputData = {};

        controller.filter(inputData);
    }

    /**
     * Test that the presenter properly updates the observers.
     */
    @Test
    public void testFilterWithObserver() {
        FilterPostViewModel viewModel = new FilterPostViewModel();
        FilterPostOutputBoundary presenter = new FilterPostPresenter(viewModel);
        PropertyChangeListener observer = evt -> {

            String property = "Search";

            assertEquals(property, evt.getPropertyName());

        };

        viewModel.addObserver(observer);
        interactor = new FilterPostInteractor(postRepository, presenter);
        controller = new FilterPostController(interactor);

        String[] inputData = {"3"};

        controller.filter(inputData);
    }

    /**
     * Test that the DS gateway correctly handles an IOException when it is thrown. Prints an error message
     * to the console.
     * Successful termination of this test indicates the exception was handled correctly.
     */
    @Test
    public void testDsGatewayCatchIOException() {
        // This file does not exist.
        String path = "bad/path.csv";
        postRepository = new FilterPostDataAccess(path);

        postRepository.getPosts();
    }

    /**
     * Test that the DS gateway correctly handles a CsvException when it is thrown. Prints an error message
     * to the console.
     * Successful termination of this test indicates the exception was handled correctly.
     */
    @Test
    public void testDsGatewayCatchCsvException() {
        // The csv file contains a row that does not have the same number of columns as the header row.
        String path = "src/test/java/filter_post/error.csv";
        postRepository = new FilterPostDataAccess(path);

        postRepository.getPosts();
    }
}
