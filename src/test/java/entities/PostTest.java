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
    Timer timer = new Timer();
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
    }

    @Test
    void getNumFavouritedUsers() {
    }

    @Test
    void addFavouritedUser() {
    }

    @Test
    void removeFavouritedUser() {
    }

    @Test
    void setTags() {
        calendar.set(2022, 11, 31, 23, 59, 59);
        Date deadline = calendar.getTime();
        Post newPost = new Post(poster, timer, title, mainDesc, tags, collaborators, deadline, replies, 0);
        newPost.setTags(new ArrayList(Arrays.asList("Sports", "Club", "Tennis", "Beginners")));
        List<String> expected = new ArrayList<>(Arrays.asList("Sports", "Club", "Tennis", "Beginners"));
        assertTrue(newPost.getTags().contains("Beginners"));
    }

    @Test
    void testEquals() {
        calendar.set(2022, 11, 31, 23, 59, 59);
        Date deadline = calendar.getTime();
        Post newPost = new Post(poster, timer, title, mainDesc, tags, collaborators, deadline, replies, 0);
        Post aliasPost = newPost;
        Post newPost2 = new Post(poster, timer, title, mainDesc, tags, collaborators, deadline, replies, 0);
        assertFalse(newPost.equals(newPost2));
        assertTrue(newPost.equals(aliasPost));
    }
}