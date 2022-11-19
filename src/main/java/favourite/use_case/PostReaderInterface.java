package favourite.use_case;

import entities.Post;

import java.util.ArrayList;

public interface PostReaderInterface {
    /**
     * Converts csv String data for a Post into a Post Object
     *
     * @param postdata ArrayList of String data stored in the database for a post
     * @return a Post object with instance variables stored in postdata
     */
    Post readPost(ArrayList postdata);
}
