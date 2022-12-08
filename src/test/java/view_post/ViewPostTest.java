package view_post;

import delete_post.UI.DeleteView;
import delete_post.drivers.DeletePostDataAccess;
import delete_post.interface_adapters.DeletePostController;
import delete_post.interface_adapters.DeletePostPresenter;
import delete_post.interface_adapters.DeletePostViewModel;
import delete_post.use_case.*;
import favourite.drivers.FavouriteDatabaseAccess;
import favourite.interface_adapters.FavouriteController;
import favourite.interface_adapters.FavouritePresenter;
import favourite.interface_adapters.FavouriteViewModel;
import favourite.ui.FavouriteView;
import favourite.use_case.*;
import filter_post.drivers.FilterPostDataAccess;
import filter_post.interface_adapters.FilterPostController;
import filter_post.interface_adapters.FilterPostPresenter;
import filter_post.interface_adapters.FilterPostViewModel;
import filter_post.ui.FilterPostBarView;
import filter_post.use_case.FilterPostDsGateway;
import filter_post.use_case.FilterPostInputBoundary;
import filter_post.use_case.FilterPostInteractor;
import filter_post.use_case.FilterPostOutputBoundary;
import make_comment.driver.MakeCommentDatabaseAccess;
import make_comment.interface_adapter.MakeCommentController;
import make_comment.interface_adapter.MakeCommentPresenter;
import make_comment.interface_adapter.MakeCommentViewModel;
import make_comment.ui.MakeCommentView;
import make_comment.use_case.MakeCommentDsGateway;
import make_comment.use_case.MakeCommentFactory;
import make_comment.use_case.MakeCommentInteractor;
import make_comment.use_case.MakeCommentOutputBoundary;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import view_comment.drivers.ViewCommentDatabaseAccess;
import view_comment.interface_adapters.ViewCommentController;
import view_comment.interface_adapters.ViewCommentPresenter;
import view_comment.interface_adapters.ViewCommentViewModel;
import view_comment.ui.ViewCommentView;
import view_comment.use_case.ViewCommentDsGateway;
import view_comment.use_case.ViewCommentInputBoundary;
import view_comment.use_case.ViewCommentInteractor;
import view_comment.use_case.ViewCommentOutputBoundary;
import view_post.drivers.ViewPostDatabaseAccess;
import view_post.interface_adapters.ViewPostController;
import view_post.interface_adapters.ViewPostOutputData;
import view_post.interface_adapters.ViewPostPresenter;
import view_post.interface_adapters.ViewPostViewModel;
import view_post.ui.PostListView;
import view_post.ui.ViewPostView;
import view_post.use_case.ViewPostDsGateway;
import view_post.use_case.ViewPostInputBoundary;
import view_post.use_case.ViewPostInteractor;
import view_post.use_case.ViewPostOutputBoundary;

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

    private ViewPostOutputBoundary presenter;
    private ViewPostDsGateway dataAccess;
    private ViewPostInputBoundary interactor;
    private ViewPostController controller;
    private ViewPostViewModel viewPostViewModel;

    @Before
    public void setUp() {
        String partialPath = "src/test/java/view_post/";
        dataAccess = new ViewPostDatabaseAccess(partialPath);

        // setting up viewPostView by creating favouriteView and makeCommentView
        // creating favouriteView
        FavouriteUserReaderInterface userFactory = new FavouriteUserFactory();
        FavouritePostReaderInterface postFactory = new FavouritePostFactory();
        FavouriteViewModel favouriteViewModel = new FavouriteViewModel();
        FavouritePresenter favouritePresenter = new FavouritePresenter(favouriteViewModel);
        FavouriteDatabaseAccess dataAccess = new FavouriteDatabaseAccess(postFactory, userFactory, partialPath);
        FavouriteInteractor favouriteInteractor = new FavouriteInteractor(dataAccess, favouritePresenter);
        FavouriteController favouriteController = new FavouriteController(favouriteInteractor);
        FavouriteView favouriteView = new FavouriteView(favouriteController);

        // creating makeCommentView
        MakeCommentFactory commentFactory = new MakeCommentFactory();
        MakeCommentViewModel makeCommentViewModel = new MakeCommentViewModel();
        MakeCommentOutputBoundary makeCommentPresenter = new MakeCommentPresenter(makeCommentViewModel);
        MakeCommentDsGateway makeCommentDatabaseAccess = new MakeCommentDatabaseAccess(partialPath);
        MakeCommentInteractor makeCommentInteractor = new MakeCommentInteractor(makeCommentDatabaseAccess, makeCommentPresenter, commentFactory);
        MakeCommentController makeCommentController = new MakeCommentController(makeCommentInteractor);
        MakeCommentView makeCommentView = new MakeCommentView(makeCommentController);

        DeletePostReaderInterface deletePostFactory = new DeletePostFactory();
        DeletePostViewModel deletePostViewModel = new DeletePostViewModel();
        DeletePostPresenter deletePostPresenter = new DeletePostPresenter(deletePostViewModel);
        DeletePostDsGateway deletePostDataAccess = new DeletePostDataAccess("", deletePostFactory);
        DeletePostInputBoundary deletePostInteractor = new DeletePostInteractor(deletePostPresenter, deletePostDataAccess);
        DeletePostController deletePostController = new DeletePostController(deletePostInteractor);
        DeleteView deleteView = new DeleteView(deletePostController);

        ViewCommentViewModel viewCommentViewModel = new ViewCommentViewModel();
        ViewCommentDsGateway viewCommentDatabaseAccess = new ViewCommentDatabaseAccess("");
        ViewCommentOutputBoundary viewCommentPresenter = new ViewCommentPresenter(viewCommentViewModel);
        ViewCommentInputBoundary viewCommentInteractor = new ViewCommentInteractor(viewCommentDatabaseAccess, viewCommentPresenter);
        ViewCommentController viewCommentController = new ViewCommentController(viewCommentInteractor);
        ViewCommentView viewCommentView = new ViewCommentView(viewCommentController);

        FilterPostViewModel filterPostViewModel = new FilterPostViewModel();
        FilterPostOutputBoundary filterPostPresenter = new FilterPostPresenter(filterPostViewModel);
        FilterPostDsGateway filterPostDataAccess = new FilterPostDataAccess(partialPath);
        FilterPostInputBoundary filterPostInteractor = new FilterPostInteractor(filterPostDataAccess, filterPostPresenter);
        FilterPostController filterPostController = new FilterPostController(filterPostInteractor);
        viewPostViewModel = new ViewPostViewModel();
        ViewPostPresenter viewPostPresenter = new ViewPostPresenter(viewPostViewModel);
        ViewPostDsGateway viewPostGateway = new ViewPostDatabaseAccess(partialPath);
        ViewPostInteractor viewPostInteractor = new ViewPostInteractor(viewPostGateway, viewPostPresenter);
        ViewPostController viewPostController = new ViewPostController(viewPostInteractor);
        // Requires array of preset tags
        FilterPostBarView filterPostBarView = new FilterPostBarView(new String[]{"sports", "quantum", "math"}, filterPostController);
        PostListView postListView = new PostListView(viewPostController, filterPostBarView);

        ViewPostView viewPostView = new ViewPostView(favouriteView, makeCommentView, deleteView, viewCommentView, postListView);

    }
    @After
    public void tearDown() {

    }
    @Test
    public void testRetrievePostsWithObserver(){
        PropertyChangeListener observer = evt -> {
            String propertyName = "show post";
            assertEquals(propertyName, evt.getPropertyName());
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
        presenter = responseModel -> {
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
        PropertyChangeListener observer = evt -> {
            String propertyName = "show error";
            assertEquals(propertyName, evt.getPropertyName());
        };
        viewPostViewModel.addObserver(observer);
        presenter = responseModel -> {
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
        };
        String emptyPath = "src/test/java/view_post/";
        dataAccess = new ViewPostDatabaseAccess(emptyPath);
        interactor = new ViewPostInteractor(dataAccess, presenter);
        controller = new ViewPostController(interactor);
        controller.viewPost(-543);
    }
}