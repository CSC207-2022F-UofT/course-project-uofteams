package favourite.use_case;

import entities.Post;

import java.util.ArrayList;

/**
 * PostFactory in the use case layer implements PostReaderInterface.
 * This class turns data from the database into Post entities the FavouriteInteractor can directly interact with.
 */
public class PostFactory implements PostReaderInterface{

    @Override
    public Post readPost(ArrayList postdata){
        // create new post from its new constructor using this.postdata
        return null;
    }

}
