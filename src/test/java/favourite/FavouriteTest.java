package favourite;

import entities.User;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.After;

public class FavouriteTest {
    @Before

    @After


    @Test
    public void testFavouriteSuccess(){
        String actual = responseModel.getMessage();
        assertEquals("This post has been successfully added to your favourites!", actual);
    }

    @Test
    public void testUnfavouriteSuccess(){
        String actual = responseModel.getMessage();
        assertEquals("This post has been removed from your favourites.", actual);

    }
}
