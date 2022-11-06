package entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TagTest {
    @Test
    public void testGetName() {
        Tag tag = new Tag("STARTUP");
        String actual = tag.getName();
        String expected = "STARTUP";

        assertEquals(expected, actual);
    }
}
