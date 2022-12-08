package entities;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class CurrentUserTest{
    User user;
    @Before
    public void setUp(){
        user = new User(true, 10, "test@mail.utoronto.ca", "password");
    }
    @After
    public void tearDown() {}

    @Test
    public void testIsAdmin() {
        CurrentUser.setCurrentUser(user);
        boolean actual = CurrentUser.getIsAdmin();
        assertTrue(actual);
    }

    @Test
    public void testSetGetUser() {
        CurrentUser.setCurrentUser(user);
        User expected = CurrentUser.getCurrentUser();
        User actual = user;
        assertEquals(expected, actual);
    }
}
