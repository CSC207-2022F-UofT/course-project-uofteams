package entities;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserUnitTest{
    @Test
    public void testIsAdmin() {
        User user =  new User(true, 0, "test-email", "test-password");
        boolean actual = user.isAdmin();
        boolean expected = true;

        assertEquals(expected, actual);
    }

    @Test
    public void testConstructorFavourites() {
        User user = new User(false, 0, "test-email", "test-password");
        List actual = user.getFavourites();
        List expected = new ArrayList();

        assertEquals(expected, actual);

    }

    @Test
    public void testUserId() {
        User user = new User(false, 21, "test-email", "test-password");
        int actual = user.getId();
        int expected = 21;

        assertEquals(expected, actual);
    }

    @Test
    public void testUserEqualityFalse() {
        User user = new User(false, 0, "test-email", "test-password");
        List lst = new ArrayList();
        boolean actual = user.equals(lst);
        boolean expected = false;

        assertEquals(expected, actual);
    }

    @Test
    public void testUserEqualityTrue() {
        User user = new User(false, 0, "test-email", "test-password");
        boolean actual = user.equals(user);
        boolean expected = true;

        assertEquals(expected, actual);
    }
}
