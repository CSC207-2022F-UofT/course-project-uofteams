package entities;

import org.junit.Test;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.After;
import java.util.ArrayList;
import static org.junit.Assert.*;;

public class CurrentUserTest{
    CurrentUser currentuser;
    User user;
    @BeforeEach
    public void setUp(){
        currentuser = new CurrentUser();
        user = new User(true, 10, "test@mail.utoronto.ca", "password");
    }
    @AfterEach
    public void tearDown() {}
    @Test
    public void testIsAdmin() {
        boolean actual = currentuser.getIsAdmin();
        assertTrue(actual);
    }

    @Test
    public void testSetGetUser() {
        currentuser.setCurrentUser(user);
        User expected = currentuser.getCurrentUser();
        User actual = user;
        assertEquals(expected, actual);
    }
}
