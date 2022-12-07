package delete_post.use_case;

import entities.Post;

/**
 * An interface for the PostFactory
 */
public interface PostReaderInterface {
    /**
     * Creates a new Post object based on the data stored in the database
     * @param postData an array of Strings, data stored in the database for a post
     * @return a Post object with qualities described in postData
     */
    Post readPost(String[] postData);
}