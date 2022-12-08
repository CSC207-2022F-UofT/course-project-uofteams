package use_case_general;

import entities.Post;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
* Post factory class, facilitates making Post entities.
* Has cases for if post is first time new or being remade
* */
public class PostFactory implements PostReaderInterface{
    public Post create(int poster, String title, String mainDesc, List<String> tags, String collaborators, LocalDate deadline,
                       int numPostsCreated){
        return new Post(poster, title, mainDesc, tags, collaborators, deadline, numPostsCreated);
    }


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
        String[] tagarray = postData[4].split(" ");
        List<String> tags = new ArrayList<>();
        for (String tag : tagarray) {
            tags.add(tag);
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
        String[] favids = postData[8].split(" ");
        List<Integer> favouritedUsersIDs = new ArrayList<>();
        for (String ids : favids) {
            favouritedUsersIDs.add(Integer.parseInt(ids));
        }
        // creating a List of Integers of ids of the replies (Comments) made on that post
        String[] replyids = postData[9].split(" ");
        List<Integer> repliesIDs = new ArrayList<>();
        for (String ids : replyids) {
            repliesIDs.add(Integer.parseInt(ids));
        }

        // using the variables created above to reconstruct a Post object
        return new Post(posterID, title, mainDesc, tags, collaborators, deadline, creationDate, id, favouritedUsersIDs,
                repliesIDs);
    }
}
