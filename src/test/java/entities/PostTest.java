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
    User poster = new User(false, 0, "email", "password");
    String title = "Title";
    String mainDesc = "Main Description";
    String collaborators = "I would like to play tennis doubles with some fellow beginners";
    List<String> tags = new ArrayList<>(Arrays.asList("Sports", "Club", "Tennis"));
    int numPostsCreated = 0;

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

    @Test
    public void getFavouritedUsers() {
        LocalDate deadline = LocalDate.of(2018, 12, 31);
        Post newPost = new Post(poster, title, mainDesc, tags, collaborators, deadline, 0);
        User user = new User(false, 0, "test-email", "test-password");
        newPost.addFavouritedUser(user);
        assertEquals(user, newPost.getFavouritedUsers().get(0));
    }

    @Test
    public void getNumFavouritedUsers() {
        LocalDate deadline = LocalDate.of(2018, 12, 31);
        Post newPost = new Post(poster, title, mainDesc, tags, collaborators, deadline, 0);
        User user = new User(false, 0, "test-email", "test-password");
        newPost.addFavouritedUser(user);
        assertEquals(1, newPost.getNumFavouritedUsers());
    }

    @Test
    public void addFavouritedUser() {
        LocalDate deadline = LocalDate.of(2018, 12, 31);
        Post newPost = new Post(poster, title, mainDesc, tags, collaborators, deadline, 0);
        User user = new User(false, 0, "test-email", "test-password");
        User user2 = new User(false, 1, "test-email", "test-password");
        newPost.addFavouritedUser(user);
        newPost.addFavouritedUser(user);
        assertEquals(user, newPost.getFavouritedUsers().get(0));
        assertEquals(1, newPost.getFavouritedUsers().size());
    }

    @Test
    public void removeFavouritedUser() {
        LocalDate deadline = LocalDate.of(2018, 12, 31);
        Post newPost = new Post(poster, title, mainDesc, tags, collaborators, deadline, 0);
        User user = new User(false, 0, "test-email", "test-password");
        User user2 = new User(false, 1, "test-email", "test-password");
        newPost.addFavouritedUser(user);
        newPost.removeFavouritedUser(user);
        assertEquals(0, newPost.getFavouritedUsers().size());
    }

    @Test
    public void setTags() {
        LocalDate deadline = LocalDate.of(2018, 12, 31);
        Post newPost = new Post(poster, title, mainDesc, tags, collaborators, deadline, 0);
        newPost.setTags(new ArrayList<>(Arrays.asList("Sports", "Club", "Tennis", "Beginners")));
        assertTrue(newPost.getTags().contains("Beginners"));
        newPost.setTags(new ArrayList<>(List.of()));
        assertEquals(0, newPost.getTags().size());
    }

    @Test
    public void testEquals() {
        LocalDate deadline = LocalDate.of(2018, 12, 31);
        Post newPost = new Post(poster, title, mainDesc, tags, collaborators, deadline, 0);
        Post aliasPost = newPost;
        Post newPost2 = new Post(poster, title, mainDesc, tags, collaborators, deadline, 1);
        assertNotEquals(newPost, newPost2);
        assertEquals(newPost, aliasPost);
    }

    @Test
    public void getID() {
        LocalDate deadline = LocalDate.of(2018, 12, 31);
        Post newPost = new Post(poster, title, mainDesc, tags, collaborators, deadline, 0);
        assertEquals(1, newPost.getID());
    }

    @Test
    public void getTags() {
        LocalDate deadline = LocalDate.of(2018, 12, 31);
        Post newPost = new Post(poster, title, mainDesc, tags, collaborators, deadline, 0);
        newPost.setTags(new ArrayList<>(List.of("Sports")));
        assertEquals("Sports", newPost.getTags().get(0));
    }

    @Test
    public void getDeadline() {
        LocalDate deadline = LocalDate.of(2018, 12, 31);
        Post newPost = new Post(poster, title, mainDesc, tags, collaborators, deadline, 0);
        assertEquals(deadline, newPost.getDeadline());
    }
    @Test
    public void getReply(){
        LocalDate deadline = LocalDate.of(2018, 12, 31);
        Post newPost = new Post(poster, title, mainDesc, tags, collaborators, deadline, 0);
        Comment comment = new Comment(poster, "Hello!");
        newPost.addReply(comment);
        assertEquals(comment, newPost.getReplies().get(0));
    }
    @Test
    public void addReply() {
        LocalDate deadline = LocalDate.of(2018, 12, 31);
        Post newPost = new Post(poster, title, mainDesc, tags, collaborators, deadline, 0);
        Comment comment = new Comment(poster, "Hello!");
        newPost.addReply(comment);
        newPost.addReply(comment);
        assertEquals(1, newPost.getReplies().size());
        assertEquals(comment, newPost.getReplies().get(0));
    }

    @Test
    public void removeReply() {
        LocalDate deadline = LocalDate.of(2018, 12, 31);
        Post newPost = new Post(poster, title, mainDesc, tags, collaborators, deadline, 0);
        Comment comment = new Comment(poster, "Hello!");
        newPost.addReply(comment);
        newPost.removeReply(comment);
        assertEquals(0, newPost.getReplies().size());
    }
}
