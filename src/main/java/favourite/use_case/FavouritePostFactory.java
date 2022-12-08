package favourite.use_case;

import entities.Post;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that creates new Post objects using data stored in String arrays
 */
public class FavouritePostFactory implements FavouritePostReaderInterface {

    /**
     * Creates a new Post object based on the data stored in the database
     * @param postData an array of Strings, data stored in the database for a post
     * @return a Post object with qualities described in postData
     */
    @Override
    public Post readPost(String[] postData) {
        // converting string data into acceptable types to reconstruct a Post object

        // posterID
        int posterID = Integer.parseInt(postData[1]);
        // title
        String title = postData[2];
        // mainDesc
        String mainDesc = postData[3];
        // tags
        String[] tagArray = postData[4].split(" ");
        List<String> tags = new ArrayList<>();
        for (String tag : tagArray) {
            if (!(tag.equals(""))){
                tags.add(tag);
            }
        }
        //collaborators
        String collaborators = postData[5];

        // deadline
        LocalDate deadline = LocalDate.parse(postData[6]);

        // creationDate
        LocalDate creationDate = LocalDate.parse(postData[7]);

        // id
        int id = Integer.parseInt(postData[0]);

        // creating a List of Integers of ids of the Users who favourited this Post
        String[] favIds = postData[8].split(" ");
        List<Integer> favouritedUsersIDs = new ArrayList<>();
        for (String ids : favIds) {
            if (!ids.isEmpty()){
                favouritedUsersIDs.add(Integer.parseInt(ids));
            }
        }
        // creating a List of Integers of ids of the replies (Comments) made on that post
        String[] replyIds = postData[9].split(" ");
        List<Integer> repliesIDs = new ArrayList<>();
        for (String ids : replyIds) {
            if (!ids.isEmpty()){
                repliesIDs.add(Integer.parseInt(ids));
            }
        }

        // using the variables created above to reconstruct a Post object
        return new Post(posterID, title, mainDesc, tags, collaborators, deadline, creationDate, id, favouritedUsersIDs,
                repliesIDs);
    }
}
