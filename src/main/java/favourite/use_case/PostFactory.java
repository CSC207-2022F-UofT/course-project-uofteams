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
        int posterID = Integer.parseInt(postdata[1]);
        String title = postdata[2];
        String mainDesc = postdata[3];
        String[] tagarray = postdata[4].split(" ");
        List<String> tags = new ArrayList<>();
        for (String tag : tagarray) {
            tags.add(tag);
        }
        String collaborators = postdata[5];
        // creating LocalDate object for deadline
        String[] deadlineElements = postdata[6].split(" ");
        int year1 = Integer.parseInt(deadlineElements[0]);
        int month1 = Integer.parseInt(deadlineElements[1]);
        int day1 = Integer.parseInt(deadlineElements[2]);
        LocalDate deadline = LocalDate.of(year1, month1, day1);
        // creating LocalDate object for creationDate
        String[] cdElements = postdata[7].split(" ");
        int year2 = Integer.parseInt(cdElements[0]);
        int month2 = Integer.parseInt(cdElements[1]);
        int day2 = Integer.parseInt(cdElements[2]);
        LocalDate creationDate = LocalDate.of(year2, month2, day2);
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
