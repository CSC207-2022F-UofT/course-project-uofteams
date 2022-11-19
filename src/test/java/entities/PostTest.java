package entities;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class PostTest extends Postable {
    int posterID = 1;
    String title = "Title";
    String mainDesc = "Main Description";
    String collaborators = "I would like to play tennis doubles with some fellow beginners";
    List<String> tags = new ArrayList<>(Arrays.asList("Sports", "Club", "Tennis"));

    int numPostsCreated = 0;
    LocalDate deadline = LocalDate.of(2022, 3, 1);

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {

    }

    public Post createPostForTest(){
        return new Post(posterID, title, mainDesc, tags, collaborators, deadline, numPostsCreated);
    }

    @Test
    public void getFavouritedUsers() {
        Post newPost = createPostForTest();
        User poster = newPost.getUser();
        newPost.addFavouritedUser(poster);
        assertEquals(poster, newPost.getFavouritedUsers().get(0));
    }

    @Test
    public void getNumFavouritedUsers() {
        Post newPost = createPostForTest();
        User poster = newPost.getUser();
        newPost.addFavouritedUser(poster);
        assertEquals(1, newPost.getNumFavouritedUsers());
    }

    @Test
    public void addFavouritedUser() {
        Post newPost = createPostForTest();
        User user = new User(false, 0, "test-email", "test-password");
        User user2 = new User(false, 1, "test-email", "test-password");
        newPost.addFavouritedUser(user);
        newPost.addFavouritedUser(user);
        assertEquals(1, newPost.getFavouritedUsers().size());
        assertEquals(user, newPost.getFavouritedUsers().get(0));
        newPost.addFavouritedUser(user2);
        assertEquals(2, newPost.getFavouritedUsers().size());
        assertEquals(user2, newPost.getFavouritedUsers().get(1));
    }

    @Test
    public void removeFavouritedUser() {
        Post newPost = createPostForTest();
        User user = new User(false, 0, "test-email", "test-password");
        newPost.addFavouritedUser(user);
        newPost.removeFavouritedUser(user);
        assertEquals(0, newPost.getFavouritedUsers().size());
    }

    @Test
    public void setTags() {
        Post newPost = createPostForTest();
        newPost.setTags(new ArrayList<>(Arrays.asList("Sports", "Club", "Tennis", "Beginners")));
        assertTrue(newPost.getTags().contains("Beginners"));
        newPost.setTags(new ArrayList<>(List.of()));
        assertEquals(0, newPost.getTags().size());
    }

    @Test
    public void testEquals() {
        Post newPost = createPostForTest();
        Post aliasPost = newPost;
        LocalDate deadline = LocalDate.of(2018, 12, 31);
        LocalDate creationDate = LocalDate.of(2018, 12, 31);
        Post newPost2 = new Post(posterID, title, mainDesc, tags, collaborators, deadline, 1);
        assertNotEquals(newPost, newPost2);
        assertEquals(newPost, aliasPost);
    }

    @Test
    public void getID() {
        Post newPost = createPostForTest();
        Post newPost2 = new Post(posterID, title, mainDesc, tags, collaborators, deadline, 1);
        assertEquals(1, newPost.getID());
        assertEquals(2, newPost2.getID());
    }

    @Test
    public void getTags() {
        Post newPost = createPostForTest();
        newPost.setTags(new ArrayList<>(List.of("Sports")));
        assertEquals("Sports", newPost.getTags().get(0));
    }

    @Test
    public void getDeadline() {
        LocalDate deadline = LocalDate.of(2022, 3, 1);
        Post newPost = createPostForTest();
        assertEquals(deadline, newPost.getDeadline());
    }
    @Test
    public void getReply(){
        Post newPost = createPostForTest();
        Comment comment = new Comment(posterID, "Hello!", 0);
        newPost.addReply(comment);
        assertEquals(comment, newPost.getReplies().get(0));
    }
    @Test
    public void addReply() {
        Post newPost = createPostForTest();
        Comment comment = new Comment(posterID, "Hello!", 0);
        newPost.addReply(comment);
        newPost.addReply(comment);
        assertEquals(1, newPost.getReplies().size());
        assertEquals(comment, newPost.getReplies().get(0));
    }

    @Test
    public void removeReply() {
        Post newPost = createPostForTest();
        Comment comment = new Comment(posterID, "Hello!", 0);
        newPost.addReply(comment);
        newPost.removeReply(comment);
        assertEquals(0, newPost.getReplies().size());
    }
}
