package favourite.use_case;

import entities.Post;

import java.util.ArrayList;

public interface PostWriterInterface {
    /**
     * Converts a Post Object into a format that can be saved into the database
     *
     * @param post Post object being saved into the database
     * @return an ArrayList of the post's instance variables in String form
     */
    ArrayList writepost(Post post);
}
