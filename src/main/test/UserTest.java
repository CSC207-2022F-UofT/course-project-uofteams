package entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest{
    @Test
    public void testIsAdmin() {
        User user =  new User(true, 0);
        boolean actual = user.isAdmin();
        boolean expected = true;

        assertEquals(expected, actual);
    }

    @Test
    public void testConstructerFavourites() {
        User user = new User(false, 0);
        boolean actual = user.getFavourites;
        boolean expected = true;

        assertEquals(expected, actual);
    }

    @Test
    public void testUserId() {
        User user = new User(false, 0);
        int actual = user.id;
        int expectd = 1;

        assertEquals(expectd, actual);
    }

    @Test
    public void testUserEqualityFalse() {
        User user = new User(false, 0);
        ArrayList lst = new ArrayList();
        boolean actual = user.equals(lst);
        boolean expected = false;

        assertEquals(expected, actual);
    }

    @Test
    public void testUserEqualityTrue() {
        User user = new User(false, 0);
        User userTwo = user;
        boolean actual = user.equals(userTwo);
        boolean expected = true;

        assertEquals(expected, actual);
    }
}