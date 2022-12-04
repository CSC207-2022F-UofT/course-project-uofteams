package view_post;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import view_post.drivers.ViewPostDatabaseAccess;
import view_post.interface_adapters.ViewPostController;
import view_post.interface_adapters.ViewPostViewModel;
import view_post.ui.ViewPostView;
import view_post.use_case.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Tests the view_post use case.
 */
public class ViewPostTest {

    private String partialPath = "src/test/java/view_post/";
    private ViewPostOutputBoundary presenter;
    private ViewPostDsGateway dataAccess;
    private ViewPostInputBoundary interactor;
    private ViewPostController controller;
    private ViewPostViewModel viewPostViewModel;
    private ViewPostView viewPostView;

    @Before
    public void setUp() {
        dataAccess = new ViewPostDatabaseAccess(partialPath);
        viewPostView = new ViewPostView();
        viewPostViewModel = new ViewPostViewModel(viewPostView);
    }
    @After
    public void tearDown() {

    }

    /**
     * test that a post is being retrieved correctly from the database.
     */
    @Test
    public void testRetrievePosts(){
        PropertyChangeListener observer = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                String propertyName = "show post";
                assertEquals(propertyName, evt.getPropertyName());
            }
        };
        viewPostViewModel.addObserver(observer);
        presenter = new ViewPostOutputBoundary() {
            @Override
            public void updateActivePost(ViewPostResponseModel responseModel) {
                String postEmail = responseModel.getPosterEmail();
                String postBody = responseModel.getPostBody();
                String postTags = responseModel.getPostTags();
                String deadline = responseModel.getDeadline();
                String creationDate = responseModel.getCreationDate();
                String collaborators = responseModel.getCollaborators();
                int postID = responseModel.getPostID();
                String title = responseModel.getTitle();
                assertEquals("test@mail.utoronto.ca", postEmail);
                assertEquals("test", postBody);
                assertEquals("test1, test2", postTags);
                assertEquals("2023-01-31", deadline);
                assertEquals("2022-12-31", creationDate);
                assertEquals("test", collaborators);
                assertEquals(1, postID);
                assertEquals("test", title);
            }
        };
        interactor = new ViewPostInteractor(dataAccess, presenter);
        controller = new ViewPostController(interactor);
        controller.viewPost(1);
    }

    /**
     * test that the correct PropertyChangeEvent is fired by the ViewPostViewModel when a post cannot be found.
     */
    @Test
    public void testRetrieveEmptyPosts(){
        PropertyChangeListener observer = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                String propertyName = "show error";
                assertEquals(propertyName, evt.getPropertyName());
            }
        };
        viewPostViewModel.addObserver(observer);
        presenter = new ViewPostOutputBoundary() {
            @Override
            public void updateActivePost(ViewPostResponseModel responseModel) {
                String postEmail = responseModel.getPosterEmail();
                String postBody = responseModel.getPostBody();
                String postTags = responseModel.getPostTags();
                String deadline = responseModel.getDeadline();
                String creationDate = responseModel.getCreationDate();
                String collaborators = responseModel.getCollaborators();
                int postID = responseModel.getPostID();
                String title = responseModel.getTitle();
                assertEquals("", postEmail);
                assertEquals("", postBody);
                assertEquals("", postTags);
                ArrayList<Integer> testPostReplies = new ArrayList<>(Arrays.asList());
                assertEquals("", deadline);
                assertEquals("", creationDate);
                assertEquals("", collaborators);
                assertEquals(-1, postID);
                assertEquals("", title);
            }
        };
        String emptyPath = "src/test/java/view_post/";
        dataAccess = new ViewPostDatabaseAccess(emptyPath);
        interactor = new ViewPostInteractor(dataAccess, presenter);
        controller = new ViewPostController(interactor);
        controller.viewPost(-543);
    }

    @Test
    public void testViewPostDatabaseAccessCatchIOException(){
        PropertyChangeListener observer = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                String propertyName = "show error";
                assertEquals(propertyName, evt.getPropertyName());
            }
        };
        viewPostViewModel.addObserver(observer);
        presenter = new ViewPostOutputBoundary() {
            @Override
            public void updateActivePost(ViewPostResponseModel responseModel) {
                String postEmail = responseModel.getPosterEmail();
                String postBody = responseModel.getPostBody();
                String postTags = responseModel.getPostTags();
                String deadline = responseModel.getDeadline();
                String creationDate = responseModel.getCreationDate();
                String collaborators = responseModel.getCollaborators();
                int postID = responseModel.getPostID();
                String title = responseModel.getTitle();
                assertEquals("", postEmail);
                assertEquals("", postBody);
                assertEquals("", postTags);
                ArrayList<Integer> testPostReplies = new ArrayList<>(Arrays.asList());
                assertEquals("", deadline);
                assertEquals("", creationDate);
                assertEquals("", collaborators);
                assertEquals(-1, postID);
                assertEquals("", title);
            }
        };
        String badPath = "/bad/path.csv";
        dataAccess = new ViewPostDatabaseAccess(badPath);
        interactor = new ViewPostInteractor(dataAccess, presenter);
        controller = new ViewPostController(interactor);
        controller.viewPost(1);
    }
}
