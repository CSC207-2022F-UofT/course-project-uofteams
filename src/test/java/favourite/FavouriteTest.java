package favourite;

import entities.CurrentUser;
import entities.Post;
import entities.User;
import favourite.drivers.FavouriteDatabaseAccess;
import favourite.interface_adapters.FavouriteController;
import favourite.interface_adapters.FavouritePresenter;
import favourite.interface_adapters.FavouriteViewModel;
import favourite.use_case.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the Favourite use case.
 * I did not test the UI (view) because I did not know how to do it in an automated test file.
 * However, I did manually test it in the dev process of the UI ViewPost use case as they are integrated.
 *
 * Line Coverage per Layer/Package
 * drivers: 88% -> didn't test the lines that caught CsvExceptions because I would need to corrupt my database files to
 * trigger the Exception
 * interface_adapters: 100%
 * use_case: 100%
 */
public class FavouriteTest {
    FavouriteInputBoundary interactor;
    FavouriteController controller;
    FavouriteOutputBoundary presenter;
    FavouriteDatabaseAccess dataAccess;
    FavouriteDsGateway simpleDataAccess;
    CurrentUser currentUser;
    PropertyChangeListener propertyChangeListener;
    FavouriteViewModel viewModel;

    @Before
    public void setUp(){
        PostReaderInterface postFactory = new PostFactory();
        UserReaderInterface userFactory = new UserFactory();
        String partialPath = "src/test/java/favourite/";
        dataAccess = new FavouriteDatabaseAccess(postFactory, userFactory, partialPath);

        User user = new User(false, 1, "email@mail.utoronto.ca", "password");
        currentUser = new CurrentUser();
        currentUser.setCurrentUser(user);
        }

    @After
    public void tearDown(){}


    /**
     * Tests whether data from the database is property converted into a Post Object
     */
    @Test
    public void testDataToPost(){
        Post actualPost = dataAccess.getPost(3);
        assertEquals("Join my dance club!", actualPost.getTitle());
        assertEquals("This is the description of my project. Who wants to join me?", actualPost.getBody());
        assertEquals(3, actualPost.getUser());
        assertEquals(0, actualPost.getReplies().size());
    }

    /**
     * Tests whether data from the database is property converted into a User Object
     */
    @Test
    public void testDataToUser(){
        User actualUser = dataAccess.getUser(2);
        assertEquals("email2@mail.utoronto.ca", actualUser.getEmail());
        assertEquals(false, actualUser.isAdmin());
        assertEquals(1, actualUser.getFavourites().size());
    }

    /**
     * Tests whether a post is "favourited" if the Post is not on the User's favourites list.
     * (Also looks at whether the updated data is property converted to a savable String[] format)
     */
    @Test
    public void testFavourite(){
        presenter = new FavouriteOutputBoundary() {
            @Override
            public void present(FavouriteResponseModel responseModel) {
                assertEquals(true, responseModel.getFavouritedBool());
                assertEquals(false, responseModel.getUnfavouritedBool());
            }
        };
        simpleDataAccess = new FavouriteDsGateway() {

            @Override
            public User getUser(int userid) {
                User user = new User(false, 1, "email@mail.utoronto.ca", "password");
                return user;
            }

            @Override
            public Post getPost(int postid) {
                ArrayList<String> tags = new ArrayList<>();
                LocalDate deadline = LocalDate.parse("2022-12-25");
                LocalDate creationDate = LocalDate.parse("2022-11-30");
                Post post = new Post(2, "title", "description", tags, "", deadline,
                        creationDate, 4, new ArrayList<Integer>(), new ArrayList<Integer>());
                return post;
            }

            @Override
            public void saveUserInfo(String[] updateduser, int userid) {
                String[] expectedArray = {"1", "false", "email@mail.utoronto.ca", "password", "", "4"};
                assertArrayEquals(expectedArray, updateduser);
            }

            @Override
            public void savePostInfo(String[] updatedpost, int postid) {
                String[] expectedArray = {"4", "2", "title", "description", "", "", "2022-12-25", "2022-11-30", "1", ""};
                System.out.println(Arrays.toString(expectedArray));
                System.out.println(Arrays.toString(updatedpost));
                assertArrayEquals(expectedArray, updatedpost);
            }
        };
        interactor = new FavouriteInteractor(simpleDataAccess, presenter);
        controller = new FavouriteController(interactor);
        controller.favourite(4);
    }

    /**
     * Tests whether a post is "unfavourited" if the Post is already on the User's favourites list.
     * (Also looks at whether the updated data is property converted to a savable String[] format)
     *
     * This test slightly differes from testFavourite() as the sample User & Post have 2 or more items
     * in each array category to expand code line coverage in the interactor.
     */
    @Test
    public void testUnfavourite(){
        presenter = new FavouriteOutputBoundary() {
            @Override
            public void present(FavouriteResponseModel responseModel) {
                assertEquals(false, responseModel.getFavouritedBool());
                assertEquals(true, responseModel.getUnfavouritedBool());
            }
        };
        simpleDataAccess = new FavouriteDsGateway() {

            @Override
            public User getUser(int userid) {
                ArrayList<Integer> favPosts = new ArrayList<>();
                favPosts.add(3000);
                favPosts.add(25);
                ArrayList<Integer> ownPosts = new ArrayList<>();
                ownPosts.add(7);
                ownPosts.add(8);
                User user = new User(false, 1, "email@mail.utoronto.ca", "password",
                        ownPosts, favPosts);
                user.addFavourite(4);
                return user;
            }

            @Override
            public Post getPost(int postid) {
                ArrayList<String> tags = new ArrayList<>();
                tags.add("tag1");
                tags.add("tag2");
                tags.add("tag3");
                ArrayList<Integer> favPost = new ArrayList<>();
                favPost.add(20);
                favPost.add(30);
                favPost.add(40);
                ArrayList<Integer> comments = new ArrayList<>();
                comments.add(3);
                comments.add(100);
                comments.add(123);
                LocalDate deadline = LocalDate.parse("2022-12-25");
                LocalDate creationDate = LocalDate.parse("2022-11-30");
                Post post = new Post(2, "title", "description", tags, "", deadline,
                        creationDate, 4, favPost, comments);
                post.addFavouritedUser(1);
                return post;
            }

            @Override
            public void saveUserInfo(String[] updateduser, int userid) {
                String[] expectedArray = {"1", "false", "email@mail.utoronto.ca", "password", "7 8", "3000 25"};
                assertArrayEquals(expectedArray, updateduser);
            }

            @Override
            public void savePostInfo(String[] updatedpost, int postid) {
                String[] expectedArray = {"4", "2", "title", "description", "tag1 tag2 tag3", "", "2022-12-25",
                        "2022-11-30", "20 30 40", "3 100 123"};
                assertArrayEquals(expectedArray, updatedpost);
            }
        };
        interactor = new FavouriteInteractor(simpleDataAccess, presenter);
        controller = new FavouriteController(interactor);
        controller.favourite(4);
    }

    /**
     * Test to see if the data is updated so that the User ID of the User who favourited/unfavourited the post is
     * added/removed from the Post's list of favourited users
     */
    @Test
    public void testSavePostInfo(){
        // user 9 has favourited post 4
        String[] updatedPost1 = {"4", "2", "UofT Merch Company", "This is the description of my project. " +
                "Who wants to join me?", "tag1 tag3", "", "2022-12-25", "2022-11-11", "4 9", "5 10"};

        dataAccess.savePostInfo(updatedPost1, 4);
        Post postFromDB1 = dataAccess.getPost(4);
        List<Integer> favUsers1 = postFromDB1.getFavouritedUsers();
        assertTrue(favUsers1.contains(9));

        // assume this same user 9 has now unfavourited post 4
        // doing this so that the database goes back to its original state so the test can be run again
        String[] updatedPost2 = {"4", "2", "UofT Merch Company", "This is the description of my project. " +
                "Who wants to join me?", "tag1 tag3", "", "2022-12-25", "2022-11-11", "4", "5 10"};

        dataAccess.savePostInfo(updatedPost2, 4);
        Post postFromDB2 = dataAccess.getPost(4);
        List<Integer> favUsers2 = postFromDB2.getFavouritedUsers();
        assertFalse(favUsers2.contains(9));
    }

    /**
     * Test to see if the data is updated so that the Post ID of the Post the User favourited/unfavourited is
     * added/removed from the User's list of favourited posts
     */
    @Test
    public void testSaveUserInfo(){
        // user 3 has favourited post 103
        String[] updatedUser1 = {"3", "true", "email3@mail.utoronto.ca", "iloveapples", "3", "103"};

        dataAccess.saveUserInfo(updatedUser1, 3);
        User userFromDB1 = dataAccess.getUser(3);
        List<Integer> favPosts1 = userFromDB1.getFavourites();
        assertTrue(favPosts1.contains(103));

        // assume this same user 3 has now unfavourited post 103
        // doing this so that the database goes back to its original state so the test can be run again
        String[] updatedUser2 = {"3", "true", "email3@mail.utoronto.ca", "iloveapples", "3", ""};

        dataAccess.saveUserInfo(updatedUser2, 3);
        User userFromDB2 = dataAccess.getUser(3);
        List<Integer> favPosts2 = userFromDB2.getFavourites();
        assertFalse(favPosts2.contains(103));
        assertTrue(favPosts2.isEmpty());
    }

    /**
     * Test to see if the right property change is fired when a post is unfavourited.
     */
    @Test
    public void testUnfavouritedFirePropertyChange(){
        interactor = new FavouriteInputBoundary() {
            @Override
            public void favouritepost(FavouriteRequestModel requestModel) {
                FavouriteResponseModel responseModel = new FavouriteResponseModel(false, true);
                presenter.present(responseModel);
            }
        };

        // creating a replacement for the FavouriteView with PropertyChangeListener to catch
        // the PropertyChange fired in the View Model
        propertyChangeListener = new PropertyChangeListener(){
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                assertTrue(evt.getPropertyName()=="unfavourited");
            }
        };
        controller = new FavouriteController(interactor);
        viewModel = new FavouriteViewModel();
        viewModel.addObserver(propertyChangeListener);
        presenter = new FavouritePresenter(viewModel);

        controller.favourite(4);
    }

    /**
     * Test to see if the right property change is fired when a post is favourited.
     */
    @Test
    public void testFavouritedFirePropertyChange(){
        interactor = new FavouriteInputBoundary() {
            @Override
            public void favouritepost(FavouriteRequestModel requestModel) {
                FavouriteResponseModel responseModel = new FavouriteResponseModel(true, false);
                presenter.present(responseModel);
            }
        };

        // creating a replacement for the FavouriteView with PropertyChangeListener to catch
        // the PropertyChange fired in the View Model
        propertyChangeListener = new PropertyChangeListener(){
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                assertTrue(evt.getPropertyName()=="favourited");
            }
        };
        controller = new FavouriteController(interactor);
        viewModel = new FavouriteViewModel();
        viewModel.addObserver(propertyChangeListener);
        presenter = new FavouritePresenter(viewModel);

        controller.favourite(4);
        }

    /**
     * Test to see if IOExceptions are properly caught in the DataAccess & not causing any problems
     */
    @Test
    public void testIOExceptionCatching(){
        // creating new database access that is given the wrong path to trigger an IOException
        PostReaderInterface postFactory = new PostFactory();
        UserReaderInterface userFactory = new UserFactory();
        String partialPath = "src/test/java/";
        FavouriteDatabaseAccess newDataAccess = new FavouriteDatabaseAccess(postFactory, userFactory, partialPath);

        String[] post = {"4", "2", "UofT Merch Company", "This is the description of my project. " +
                "Who wants to join me?", "tag1 tag3", "", "2022-12-25", "2022-11-11", "4", "5 10"};
        String[] user = {"3", "true", "email3@mail.utoronto.ca", "iloveapples", "3", ""};

        newDataAccess.getPost(2);
        newDataAccess.getUser(2);
        newDataAccess.savePostInfo(post, 4);
        newDataAccess.saveUserInfo(user, 3);
    }

}



