package favourite.drivers;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import entities.Post;
import entities.User;
import favourite.use_case.FavouriteDSGateway;
import favourite.use_case.PostReaderInterface;
import favourite.use_case.UserReaderInterface;

import java.io.FileWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

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
    private final Path userFilePath;
    private final Path currentUserFilePath;
    private final Path postFilePath;
    /**
     * Initializes DataAccess
     *
     * @param postreader an instance of PostReaderInterface
     * @param userreader an instance of UserReaderInterface
     */
    public DataAccess(PostReaderInterface postreader, UserReaderInterface userreader, String userFilePath,
                      String currentUserFilePath, String postFilePath){
        this.postreader = postreader;
        this.userreader = userreader;
        this.userFilePath = Paths.get(userFilePath);
        this.currentUserFilePath = Paths.get(currentUserFilePath);
        this.postFilePath = Paths.get(postFilePath);
    }

    /**
     * Retrieves current user as a User object from the database
     * @return the current user
     */
    @Override
    public User getUser(){
        // finding the current user's id
        List<String[]> currentUser = readAllLines(currentUserFilePath);
        int userID = Integer.parseInt(currentUser[1][0]);

        // finding and retrieving the current user's data
        List<String[]> allUsers = readAllLines(userFilePath);
        String[] userdata;
        for (String[] user: allUsers){
            int id = Integer.parseInt(user[0]);
            if (id==userID) {
                userdata = user;
            }
        }

        // turning the current user's data into a user object
        return userreader.readUser(userdata);
    }

    /**
     * Retrieves the post being favourited/unfavourited as a Post object from the database
     *
     * @param postid the id of the post being favourited/unfavourited
     * @return a Post
     */
    @Override
    public Post getPost(int postid){
        // finding and retrieving the current user's data
        List<String[]> allPosts = readAllLines(postFilePath);
        String[] postdata;
        for (String[] post: allPosts) {
            try{
                int id = Integer.parseInt(post[0]);
                if (id == postid) {
                    postdata = post;
                }
            }catch(NumberFormatException ex){
            }
        }
        return postreader.readPost(postdata);
    }

    /**
     * Reads all the data from a csv file
     * @param path the path of the csv file being read
     * @return all the data from a csv file in a List of String Arrays
     */
    private List<String[]> readAllLines(Path path){
        Reader reader = Files.newBufferedReader(path);
        CSVReader csvReader = new CSVReader(reader);
        return csvReader.readAll();
    }

    /**
     * Rewrites entire csv with the updated User
     * @param updateduser data of the updated user
     * @param userid id of the updated user
     */
    @Override
    public void saveUserInfo(String[] updateduser, int userid) {
        // re-reading all user data
        List<String[]> userdata = readAllLines(userFilePath);

        // identifying index of the user in the old data
        int i = 1; // indexes from 1 because the first line is a header
        boolean linefound = FALSE;
        while (i < userdata.size() && linefound==FALSE){
            int id = Integer.parseInt(userdata[i][0]);
            if (id == userid){
                linefound = TRUE;
            }
            i+=1;
        }
        userdata.remove(i-1);
        userdata.add(updateduser);
        writeAllLines(userFilePath, userdata);
    }

    /**
     * Rewrites entire csv with the updated Post
     * @param updatedpost data of the updated post
     * @param postid id of the updated post
     */
    @Override
    public void savePostInfo(String[] updatedpost, int postid){
        // re-reading all post data
        List<String[]> postdata = readAllLines(postFilePath);

        // identifying index of the user in the old data
        int i = 1; // indexes from 1 because the first line is a header
        boolean linefound = FALSE;
        while (i < postdata.size() && linefound==FALSE){
            int id = Integer.parseInt(postdata[i][0]);
            if (id == postid){
                linefound = TRUE;
            }
            i+=1;
        }
        postdata.remove(i-1);
        postdata.add(updatedpost);
        writeAllLines(postFilePath, postdata);
    }

    /**
     * Writes all the data into a csv file
     * @param path the path of the csv file being written into
     */
    private void writeAllLines(Path path, List<String[]> data){
        CSVWriter writer = new CSVWriter(new FileWriter(path.toString()));
        writer.writeAll(data);
    }
}
