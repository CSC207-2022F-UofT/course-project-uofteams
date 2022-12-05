package favourite;

import entities.CurrentUser;
import entities.Post;
import entities.User;
import favourite.drivers.FavouriteDatabaseAccess;
import favourite.interface_adapters.FavouriteController;
import favourite.use_case.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class FavouriteTest {
    FavouriteInputBoundary interactor;
    FavouriteController controller;
    FavouriteOutputBoundary presenter;
    FavouriteDatabaseAccess dataAccess;
    FavouriteDsGateway simpleDataAccess;
    CurrentUser currentUser;

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
                User user = new User(false, 1, "email@mail.utoronto.ca", "password");
                user.addFavourite(4);
                return user;
            }

            @Override
            public Post getPost(int postid) {
                ArrayList<String> tags = new ArrayList<>();
                LocalDate deadline = LocalDate.parse("2022-12-25");
                LocalDate creationDate = LocalDate.parse("2022-11-30");
                Post post = new Post(2, "title", "description", tags, "", deadline,
                        creationDate, 4, new ArrayList<Integer>(), new ArrayList<Integer>());
                post.addFavouritedUser(1);
                return post;
            }

            @Override
            public void saveUserInfo(String[] updateduser, int userid) {
                String[] expectedArray = {"1", "false", "email@mail.utoronto.ca", "password", "", ""};
                assertArrayEquals(expectedArray, updateduser);
            }

            @Override
            public void savePostInfo(String[] updatedpost, int postid) {
                String[] expectedArray = {"4", "2", "title", "description", "", "", "2022-12-25", "2022-11-30", "", ""};
                assertArrayEquals(expectedArray, updatedpost);
            }
        };
        interactor = new FavouriteInteractor(simpleDataAccess, presenter);
        controller = new FavouriteController(interactor);
        controller.favourite(4);
    }


}
