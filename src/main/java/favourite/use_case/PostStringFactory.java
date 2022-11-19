package favourite.use_case;

import entities.Post;

import java.util.ArrayList;

/**
 * PostStringFactory in the use case layer implements PostWriterInterface.
 * It converts Post objects into a format that the database can save.
 */
public class PostStringFactory implements PostWriterInterface {
    @Override
    public ArrayList writepost(Post post) {
        return null;
    }
}
