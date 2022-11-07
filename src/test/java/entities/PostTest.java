package entities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PostTest extends Postable {
    User poster = new User();
    String title = "Title";
    String mainDesc = "Main Description";
    String collaborators = "I would like to play tennis doubles with some fellow beginners"
    List<String> tags = new ArrayList(Arrays.asList("Sports", "Club", "Tennis"));
    Calendar calendar = Calendar.getInstance();
    Comment reply1 = new Comment();
    List<Comment> replies = new ArrayList<>(Arrays.asList(reply1));
    int numPostsCreated = 0;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getFavouritedUsers() {
        calendar.set(2022, 11, 31, 23, 59, 59);
        Date deadline = calendar.getTime();
        Post newPost = new Post(poster, title, mainDesc, tags, collaborators, deadline, 0);
        User user = new User(false, 0, "test-email", "test-password");
        newPost.addFavouritedUser(user);
        assertEquals(user, newPost.getFavouritedUsers().get(0));
    }

    @Test
    void getNumFavouritedUsers() {
        calendar.set(2022, 11, 31, 23, 59, 59);
        Date deadline = calendar.getTime();
        Post newPost = new Post(poster, title, mainDesc, tags, collaborators, deadline, 0);
        User user = new User(false, 0, "test-email", "test-password");
        newPost.addFavouritedUser(user);
        assertEquals(1, newPost.getNumFavouritedUsers());
    }

    @Test
    void addFavouritedUser() {
        calendar.set(2022, 11, 31, 23, 59, 59);
        Date deadline = calendar.getTime();
        Post newPost = new Post(poster, title, mainDesc, tags, collaborators, deadline, 0);
        User user = new User(false, 0, "test-email", "test-password");
        User user2 = new User(false, 1, "test-email", "test-password");
        newPost.addFavouritedUser(user);
        newPost.addFavouritedUser(user);
        assertEquals(user, newPost.getFavouritedUsers().get(0));
        assertEquals(1, newPost.getFavouritedUsers().size());
    }

    @Test
    void removeFavouritedUser() {
        calendar.set(2022, 11, 31, 23, 59, 59);
        Date deadline = calendar.getTime();
        Post newPost = new Post(poster, title, mainDesc, tags, collaborators, deadline, 0);
        User user = new User(false, 0, "test-email", "test-password");
        User user2 = new User(false, 1, "test-email", "test-password");
        newPost.addFavouritedUser(user);
        newPost.removeFavouritedUser(user);
        assertEquals(0, newPost.getFavouritedUsers().size());
    }

    @Test
    void setTags() {
        calendar.set(2022, 11, 31, 23, 59, 59);
        Date deadline = calendar.getTime();
        Post newPost = new Post(poster, title, mainDesc, tags, collaborators, deadline, 0);
        newPost.setTags(new ArrayList(Arrays.asList("Sports", "Club", "Tennis", "Beginners")));
        assertTrue(newPost.getTags().contains("Beginners"));
        newPost.setTags(new ArrayList(Arrays.asList()));
        assertTrue(newPost.getTags().size() == 0);
    }

    @Test
    void testEquals() {
        calendar.set(2022, 11, 31, 23, 59, 59);
        Date deadline = calendar.getTime();
        Post newPost = new Post(poster, title, mainDesc, tags, collaborators, deadline, 0);
        Post aliasPost = newPost;
        Post newPost2 = new Post(poster, title, mainDesc, tags, collaborators, deadline, 1);
        assertFalse(newPost.equals(newPost2));
        assertTrue(newPost.equals(aliasPost));
    }

    @Test
    void getID() {
        calendar.set(2022, 11, 31, 23, 59, 59);
        Date deadline = calendar.getTime();
        Post newPost = new Post(poster, title, mainDesc, tags, collaborators, deadline, 0);
        assertEquals(1, newPost.getID());
    }

    @Test
    void getTags() {
        calendar.set(2022, 11, 31, 23, 59, 59);
        Date deadline = calendar.getTime();
        Post newPost = new Post(poster, title, mainDesc, tags, collaborators, deadline, 0);
        newPost.setTags(new ArrayList(Arrays.asList("Sports")));
        assertEquals("Sports", newPost.getTags().get(0));
    }

    @Test
    void getDeadline() {
        calendar.set(2022, 11, 31, 23, 59, 59);
        Date deadline = calendar.getTime();
        Post newPost = new Post(poster, title, mainDesc, tags, collaborators, deadline, 0);
        assertEquals(deadline, newPost.getDeadline());
    }
    @Test
    void getReply(){
        calendar.set(2022, 11, 31, 23, 59, 59);
        Date deadline = calendar.getTime();
        Post newPost = new Post(poster, title, mainDesc, tags, collaborators, deadline, 0);
        Comment comment = new Comment(poster, "Hello!", 1);
        newPost.addReply(comment);
        assertEquals(comment, newPost.getReplies().get(0));
    }
    @Test
    void addReply() {
        calendar.set(2022, 11, 31, 23, 59, 59);
        Date deadline = calendar.getTime();
        Post newPost = new Post(poster, title, mainDesc, tags, collaborators, deadline, 0);
        Comment comment = new Comment(poster, "Hello!", 1);
        newPost.addReply(comment);
        newPost.addReply(comment);
        assertEquals(1, newPost.getReplies().size());
        assertEquals(comment, newPost.getReplies().get(0));
    }

    @Test
    void removeReply() {
        calendar.set(2022, 11, 31, 23, 59, 59);
        Date deadline = calendar.getTime();
        Post newPost = new Post(poster, title, mainDesc, tags, collaborators, deadline, 0);
        Comment comment = new Comment(poster, "Hello!", 1);
        newPost.addReply(comment);
        newPost.removeReply(comment);
        assertEquals(0, newPost.getReplies().size());
    }
}