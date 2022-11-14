package entities;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

public class TagTest {
    User p;
    Calendar c;
    Date d;
    List <String> tags;
    Post sample;
    Tag tag;

    @Before
    public void setUp() {
        p = new User(false, 0, "test@email.com", "moogah");
        c = Calendar.getInstance();
        c.set(2022, Calendar.DECEMBER, 31, 23, 59, 59);
        d = c.getTime();
        tags = new ArrayList<>();
        tags.add("TEST TAG");
        sample = new Post(
                p, "Test", "Test", tags, "", d, 0
        );
        tag = new Tag("TEST TAG");
    }

    @After
    public void tearDown() {}

    /**
     * Tests whether Tag.getName correctly returns the tag name.
     */
    @Test
    public void testGetName() {
        String actual = tag.getName();
        String expected = "TEST TAG";

        assertEquals(expected, actual);
    }

    /**
     * Tests whether Tag.addPostToTag correctly adds a new post to the tag.
     */
    @Test
    public void testAddPostToTag() {
        boolean expected = tag.addPostToTag(sample);

        assertTrue(expected);
    }

    /**
     * Tests whether Tag.addPostToTag correctly does not add a duplicate post to the tag.
     */
    @Test
    public void testAddDuplicatePostToTag() {
        tag.addPostToTag(sample);
        boolean expected = tag.addPostToTag(sample);

        assertFalse(expected);
    }

    /**
     * Tests whether Tag.removePostFromTag correctly removes a post from this tag.
     */
    @Test
    public void testRemovePostFromTag() {
        tag.addPostToTag(sample);
        boolean expected = tag.removePostFromTag(sample);

        assertTrue(expected);
    }

    /**
     * Tests whether Tag.removePostFromTag correctly does nothing when the post is not in this tag.
     */
    @Test
    public void testRemovePostNotInTag() {
        boolean expected = tag.removePostFromTag(sample);

        assertFalse(expected);
    }

    /**
     * Tests whether Tag.getTaggedPosts correctly returns a list of posts with this tag.
     * This test will simply check that the returned list is of the correct size.
     */
    @Test
    public void testGetTaggedPosts() {
        for (int i = 0; i < 5; i++) {
            Post temp = new Post(p, "Test" + i, "Test" + i, tags, "", d, i);
            tag.addPostToTag(temp);
        }
        int actual = tag.getTaggedPosts().size();
        int expected = 5;

        assertEquals(expected, actual);
    }
}
