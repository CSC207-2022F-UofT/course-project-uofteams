package favourite.drivers;

import entities.Post;
import entities.User;
import favourite.use_case.*;

import java.util.ArrayList;

/**
 * The DataAccess class in the driver layer implements FavouriteDataAccessInterface.
 * The main functionality of this class is retrieving and saving data to/from the database.
 */

public class DataAccess implements FavouriteDSGateway {
    // postreader is used to allow class DataAccess to access PostFactory to convert data into
    // Post entity in getPost method
    private final PostReaderInterface postreader;
    // userreader is used to allow class DataAccess to access UserFactory to convert data into
    // User entity in getUser method
    private final UserReaderInterface userreader;
    // postwriter is used to allow class DataAccess to access PostStringFactory to convert Post
    // entity back into a list of strings to save it in the database in the savePostInfo method
    private final PostWriterInterface postwriter;
    // userwriter is used to allow class DataAccess to access UserStringFactory to convert User
    // entity back into a list of strings to save it in the database in the saveUserInfo method
    private final UserWriterInterface userwriter;

    /**
     * Initializes DataAccess
     *
     * @param postreader an instance of PostReaderInterface
     * @param userreader an instance of UserReaderInterface
     * @param postwriter an instance of PostWriterInterface
     * @param userwriter an instance of UserWriterInterface
     */
    public DataAccess(PostReaderInterface postreader, UserReaderInterface userreader,
                      PostWriterInterface postwriter, UserWriterInterface userwriter){
        this.postreader = postreader;
        this.userreader = userreader;
        this.postwriter = postwriter;
        this.userwriter = userwriter;
    }

    @Override
    public User getUser(){
        ArrayList userdata =  new ArrayList();
        return userreader.readUser(userdata);
    }

    @Override
    public Post getPost(int postid){
        ArrayList postdata = new ArrayList(); // use post id to get the list from the database
        return postreader.readPost(postdata);
    }

    @Override
    public void saveUserInfo(User user) {
        ArrayList userdata = userwriter.writeuser(user);
        // save userdata to database, update once csv is set up
    }
    @Override
    public void savePostInfo(Post post, int postid){
        ArrayList postdata = postwriter.writepost(post);
        // save postdata to database, update once csv is set up
    }

}
