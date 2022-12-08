package use_case_general;

import entities.Post;

/*
* The interface which creates Post entities for posts that already exist in the database but not
* in memory
* */
public interface PostReaderInterface {
    /**
     * Converts csv String data for a Post into a Post Object
     *
     * @param postData Array of Strings, data stored in the database for a post
     * @return a Post object with instance variables stored in postdata
     */
    Post readPost(String[] postData);
}
