package view_post.drivers;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import view_post.use_case.ViewPostDsGateway;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ViewPostGateway implements ViewPostDsGateway {
    // partialpath is a String that contains the partial directory that leads to all csv files in the program
    // it is isolated from the file name so that in case the file is moved, the code is still compatible
    private final String partialPath;

    public ViewPostGateway(String partialPath){
        this.partialPath = partialPath;
    }

    /**
     * Return a list of strings representing the data of the post with matching post IDs.
     */
    public String[] getPostInfo(int postID) {
        String[] postInfo = new String[9];

        String[] rawPostData = this.getPostData(postID);
        int userID = Integer.parseInt(rawPostData[1]);
        String userEmail = this.getUserEmail(userID);

        //poster email
        postInfo[0] = userEmail;
        //post body
        postInfo[1] = rawPostData[3];
        //post tags
        postInfo[2] = rawPostData[4];
        //replies
        postInfo[3] = rawPostData[9];
        //deadline
        postInfo[4] = rawPostData[6];
        //creation date
        postInfo[5] = rawPostData[7];
        //collaborators
        postInfo[6] = rawPostData[5];
        // post id
        postInfo[7] = rawPostData[0];
        //title
        postInfo[8] = rawPostData[2];

        return postInfo;
    }
    /**
     * Retrieves email of a User from the database
     * @param userid user's ID
     * @return user's email
     */
    private String getUserEmail(int userid){
        try {
            // finding and retrieving the current user's data
            List<String[]> allUsers = readAllLines(Paths.get(partialPath+"users.csv"));
            allUsers.subList(0,1).clear();
            String[] userData = new String[6];
            for (String[] user : allUsers) {
                int id = Integer.parseInt(user[0]);
                if (id == userid) {
                    userData = user;
                }
            }

            // returns the email of the user
            return userData[2];
        }catch (IOException e1){
            System.out.println("Error occurred while accessing file");
        }catch (CsvException e2){
            System.out.println("Error occurred while reading file");
        }
        return null;
    }

    /**
     * Retrieves the post being favourited/unfavourited as a Post object from the database
     *
     * @param postid the id of the post being favourited/unfavourited
     * @return a Post
     */
    private String[] getPostData(int postid){
        try {
            // finding the post in the database
            List<String[]> allPosts = readAllLines(Paths.get(partialPath+"posts.csv"));
            allPosts.subList(0,1).clear();
            String[] postData = new String[10];
            for (String[] post : allPosts) {
                try {
                    int id = Integer.parseInt(post[0]);
                    if (id == postid) {
                        postData = post;
                    }
                } catch (NumberFormatException ex) {
                }
            }
            return postData;
        }catch (IOException e1){
            System.out.println("Error occurred while accessing file");
        }catch (CsvException e2){
            System.out.println("Error occurred while reading file");
        }
        return null;
    }

    /**
     * Reads all the data from a csv file
     * @param path the path of the csv file being read
     * @return all the data from a csv file in a List of String Arrays
     */
    private List<String[]> readAllLines(Path path) throws IOException, CsvException {
        try(Reader reader = Files.newBufferedReader(path)){
            try (CSVReader csvReader = new CSVReader(reader)){
                return csvReader.readAll();
            }
        }
    }

}
