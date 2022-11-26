package favourite.use_case;

import entities.Post;

public interface PostReaderInterface {
    /**
     * Converts csv String data for a Post into a Post Object
     *
     * @param postdata Array of Strings, data stored in the database for a post
     * @return a Post object with instance variables stored in postdata
     */
    Post readPost(String[] postdata);
}
