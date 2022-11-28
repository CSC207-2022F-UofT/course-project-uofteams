package favourite.use_case;

import entities.Post;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * PostFactory in the use case layer implements PostReaderInterface.
 * This class turns data from the database into Post entities the FavouriteInteractor can directly interact with.
 */
public class PostFactory implements PostReaderInterface{

    /**
     * Creates a new Post object based on the data stored in the database
     * @param postdata Array of Strings, data stored in the database for a post
     * @return Post object with qualities described in postdata
     */
    @Override
    public Post readPost(String[] postdata) {
        // converting string data into acceptable types to reconstruct a Post object

        // posterID
        int posterID = Integer.parseInt(postdata[1]);
        // title
        String title = postdata[2];
        // mainDesc
        String mainDesc = postdata[3];
        // tags
        String[] tagarray = postdata[4].split(" ");
        List<String> tags = new ArrayList<>();
        for (String tag : tagarray) {
            tags.add(tag);
        }
        //collaborators
        String collaborators = postdata[5];

        // deadline
        LocalDate deadline = LocalDate.parse(postdata[6]);

        // creationDate
        LocalDate creationDate = LocalDate.parse(postdata[7]);

        // id
        int id = Integer.parseInt(postdata[0]);

        // creating a List of Integers of ids of the Users who favourited this Post
        String[] favids = postdata[8].split(" ");
        List<Integer> favouritedUsersIDs = new ArrayList<>();
        for (String ids : favids) {
            favouritedUsersIDs.add(Integer.parseInt(ids));
        }
        // creating a List of Integers of ids of the replies (Comments) made on that post
        String[] replyids = postdata[9].split(" ");
        List<Integer> repliesIDs = new ArrayList<>();
        for (String ids : replyids) {
            repliesIDs.add(Integer.parseInt(ids));
        }

        // using the variables created above to reconstruct a Post object
        Post post = new Post(posterID, title, mainDesc, tags, collaborators, deadline, creationDate, id, favouritedUsersIDs,
                repliesIDs);
        return post;
    }
}
