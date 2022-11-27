//package favourite;
//
//import entities.CurrentUser;
//import entities.Post;
//import entities.User;
//import favourite.interface_adapters.FavouriteController;
//import favourite.use_case.*;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//
//public class FavouriteTest {
//    FavouriteInputBoundary interactor;
//    CurrentUser currentUser;
//    FavouriteController controller;
//    FavouriteOutputBoundary presenter;
//    FavouriteDSGateway dataAccess;
//    Post post1;
//    User user1;
//
//    @Before
//    public void setUp(){
//        // creating sample Post object post1
//        List<String> tags = new ArrayList<>();
//        tags.add("tag1");
//        tags.add("tag2");
//        LocalDate deadline = LocalDate.of(2022, 12, 25);
//        LocalDate creationDate = LocalDate.of(2022, 11, 11);
//        List<Integer> favourites = new ArrayList<>();
//        favourites.add(1);
//        favourites.add(4);
//        List<Integer> replies = new ArrayList<>();
//        post1 = new Post(2, "My new and shiny project", "This is the description of my project. " +
//                "Who wants to join me?", tags, "", deadline, creationDate, 1, favourites, replies);
//
//        // creating sample User object user1
//        List<Integer> posts = new ArrayList<>();
//        posts.add(5);
//        List<Integer> favouritePosts = new ArrayList<>();
//        favouritePosts.add(1);
//        favouritePosts.add(4);
//        user1 = new User(true, 1, "email1@mail.utoronto.ca", "12345", posts, favouritePosts);
//
//        // setting current User
//        currentUser.setCurrentUser(user1);
//
//    }
//
//    @After
//    public void tearDown(){}
//
//    /**
//     * Tests whether data from the database is read
//     */
//    @Test
//    public void testReadAllLines(){}
//
//    /**
//     * Tests whether information is being written into the database
//     */
//    @Test
//    public void testWriteAllLines(){}
//
//    /**
//     * Tests whether data from the database (in String[] form) is property converted into a Post Object
//     */
//    @Test
//    public void testDataToPost(){}
//
//    /**
//     * Tests whether data from the database (in String[] form) is property converted into a User Object
//     */
//    @Test
//    public void testDataToUser(){}
//
//    /**
//     * Tests whether Post object is properly converted into a String[] that can be saved in the database
//     */
//    @Test
//    public void testPostToData(){}
//
//    /**
//     * Tests whether User object is properly converted into a String[] that can be saved in the database
//     */
//    @Test
//    public void testUserToData(){}
//
//    /**
//     * Tests whether a post is "favourited" if the Post is not on the User's favourites list.
//     */
//    @Test
//    public void testFavourite(){
//        FavouriteRequestModel requestModel = new FavouriteRequestModel(1);
//
//
//    }
//
//    /**
//     * Tests whether a post is "unfavourited" if the Post is already on the User's favourites list.
//     */
//    @Test
//    public void testUnfavourite(){
//        interactor = new FavouriteInteractor(dataAccess, presenter);
//        controller = new FavouriteController(interactor, 1);
//        controller.favourite(1);
//
//        List<Integer> actualFavourtiedUsers = post1.getFavouritedUsers();
//        List<Integer> expectedFavouriteUsers = new ArrayList<>();
//        expectedFavouriteUsers.add(4);
//
//        List<Integer> actualFavouritePosts = user1.getFavourites();
//        List<Integer> expectedFavouritePosts = new ArrayList<>();
//        expectedFavouritePosts.add(4);
//
//        assertEquals(actualFavourtiedUsers, expectedFavouriteUsers);
//        assertEquals(actualFavouritePosts, expectedFavouritePosts);
//
//    }
//
//
//}
