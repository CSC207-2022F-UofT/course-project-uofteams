package view_post;

import favourite.drivers.FavouriteDatabaseAccess;
import favourite.interface_adapters.FavouriteController;
import favourite.interface_adapters.FavouritePresenter;
import favourite.interface_adapters.FavouriteViewModel;
import favourite.ui.FavouriteView;
import favourite.use_case.*;
import make_comment.driver.MakeCommentDatabaseAccess;
import make_comment.interface_adapter.MakeCommentController;
import make_comment.interface_adapter.MakeCommentPresenter;
import make_comment.interface_adapter.MakeCommentViewModel;
import make_comment.ui.MakeCommentView;
import make_comment.use_case.CommentFactory;
import make_comment.use_case.MakeCommentDsGateway;
import make_comment.use_case.MakeCommentInteractor;
import make_comment.use_case.MakeCommentOutputBoundary;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import view_post.drivers.ViewPostDatabaseAccess;
import view_post.interface_adapters.ViewPostController;
import view_post.interface_adapters.ViewPostOutputData;
import view_post.interface_adapters.ViewPostPresenter;
import view_post.interface_adapters.ViewPostViewModel;
import view_post.use_case.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.Assert.assertEquals;

/**
 * Tests the view_post use case.
 * Test coverage:
 * drivers: 77%
 * interface_adapters: 82%
 * use_case: 97%
 * The UI is not being tested.
 */
public class ViewPostTest {

    private String partialPath = "src/test/java/view_post/";
    private ViewPostOutputBoundary presenter;
    private ViewPostDsGateway dataAccess;
    private ViewPostInputBoundary interactor;
    private ViewPostController controller;
    private ViewPostViewModel viewPostViewModel;

    @Before
    public void setUp() {
        dataAccess = new ViewPostDatabaseAccess(partialPath);

        // setting up viewPostView by creating favouriteView and makeCommentView
        // creating favouriteView
        UserReaderInterface userFactory = new UserFactory();
        PostReaderInterface postFactory = new PostFactory();
        FavouriteViewModel favourtiteViewModel = new FavouriteViewModel();
        FavouritePresenter favouritePresenter = new FavouritePresenter(favourtiteViewModel);
        FavouriteDatabaseAccess dataAccess = new FavouriteDatabaseAccess(postFactory, userFactory, partialPath);
        FavouriteInteractor favouriteInteractor = new FavouriteInteractor(dataAccess, favouritePresenter);
        FavouriteController favouriteController = new FavouriteController(favouriteInteractor);
        FavouriteView favouriteView = new FavouriteView(favouriteController);

        // creating makeCommentView
        CommentFactory commentFactory = new CommentFactory();
        MakeCommentViewModel makeCommentViewModel = new MakeCommentViewModel();
        MakeCommentOutputBoundary makeCommentPresenter = new MakeCommentPresenter(makeCommentViewModel);
        MakeCommentDsGateway makeCommentDatabaseAccess = new MakeCommentDatabaseAccess(partialPath);
        MakeCommentInteractor makeCommentInteractor = new MakeCommentInteractor(makeCommentDatabaseAccess, makeCommentPresenter, commentFactory);
        MakeCommentController makeCommentController = new MakeCommentController(makeCommentInteractor);
        MakeCommentView makeCommentView = new MakeCommentView(makeCommentController);

        viewPostView = new ViewPostView(favouriteView, makeCommentView);
        viewPostViewModel = new ViewPostViewModel(viewPostView);

    }
    @After
    public void tearDown() {

    }
    @Test
    public void testRetrievePostsWithObserver(){
        PropertyChangeListener observer = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                String propertyName = "show post";
                assertEquals(propertyName, evt.getPropertyName());
            }
        };
        viewPostViewModel.addObserver(observer);
        presenter = new ViewPostPresenter(viewPostViewModel);
        interactor = new ViewPostInteractor(dataAccess, presenter);
        controller = new ViewPostController(interactor);
        controller.viewPost(1);
    }

    /**
     * test that a post is being retrieved correctly from the database and checks if correct PropertyChange is fired.
     */
    @Test
    public void testRetrievePosts(){
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
                assertEquals("", deadline);
                assertEquals("", creationDate);
                assertEquals("", collaborators);
                assertEquals(-1, postID);
                assertEquals("", title);

                ViewPostOutputData outputData = new ViewPostOutputData(postEmail, postBody, postTags, deadline, creationDate,
                        collaborators, postID, title);
                viewPostViewModel.updateView(outputData);
            }
        };
        String emptyPath = "src/test/java/view_post/";
        dataAccess = new ViewPostDatabaseAccess(emptyPath);
        interactor = new ViewPostInteractor(dataAccess, presenter);
        controller = new ViewPostController(interactor);
        controller.viewPost(-543);
    }
}
