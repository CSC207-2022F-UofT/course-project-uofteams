package entities;

import org.junit.Test;

import static org.junit.Assert.*;

public class TagTest {
    @Test
    public void testGetName() {
        Tag tag = new Tag("STARTUP");
        String actual = tag.getName();
        String expected = "STARTUP";

        assertEquals(expected, actual);
    }
}
