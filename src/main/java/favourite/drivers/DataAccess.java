package favourite.drivers;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import entities.Post;
import entities.User;
import favourite.use_case.FavouriteDSGateway;
import favourite.use_case.PostReaderInterface;
import favourite.use_case.UserReaderInterface;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
    private final String partialpath;
    /**
     * Initializes DataAccess
     *
     * @param postreader an instance of PostReaderInterface
     * @param userreader an instance of UserReaderInterface
     */
    public DataAccess(PostReaderInterface postreader, UserReaderInterface userreader, String partialpath){
        this.postreader = postreader;
        this.userreader = userreader;
        this.partialpath = partialpath;
    }

    /**
     * Retrieves current user as a User object from the database
     * @return the current user
     */
    @Override
    public User getUser(int userid){
        try {
            // finding and retrieving the current user's data
            List<String[]> allUsers = readAllLines(Paths.get(partialpath+"users.csv"));
            String[] userdata = new String[6];
            for (String[] user : allUsers) {
                int id = Integer.parseInt(user[0]);
                if (id == userid) {
                    userdata = user;
                }
            }

            // turning the current user's data into a user object
            return userreader.readUser(userdata);
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
    @Override
    public Post getPost(int postid){
        try {
            // finding and retrieving the current user's data
            List<String[]> allPosts = readAllLines(Paths.get(partialpath+"posts.csv"));
            String[] postdata = new String[10];
            for (String[] post : allPosts) {
                try {
                    int id = Integer.parseInt(post[0]);
                    if (id == postid) {
                        postdata = post;
                    }
                } catch (NumberFormatException ex) {
                }
            }
            return postreader.readPost(postdata);
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
    private List<String[]> readAllLines(Path path) throws IOException, CsvException{
        try(Reader reader = Files.newBufferedReader(path)){
            try (CSVReader csvReader = new CSVReader(reader)){
                return csvReader.readAll();
            }
        }
    }

    /**
     * Rewrites entire csv with the updated User
     * @param updateduser data of the updated user
     * @param userid id of the updated user
     */
    @Override
    public void saveUserInfo(String[] updateduser, int userid) {
        try {
            Path usersDB = Paths.get(partialpath+"users.csv");
            // re-reading all user data
            List<String[]> userdata = readAllLines(usersDB);

            // identifying index of the user in the old data
            int i = 1; // indexes from 1 because the first line is a header
            boolean linefound = false;
            while (i < userdata.size() && linefound == false) {
                int id = Integer.parseInt(userdata.get(i)[0]);
                if (id == userid) {
                    linefound = true;
                }
                i += 1;
            }
            userdata.remove(i - 1);
            userdata.add(updateduser);
            writeAllLines(usersDB, userdata);
        }catch (IOException e1){
            System.out.println("Error occurred while accessing file");
        }catch (CsvException e2){
            System.out.println("Error occurred while reading file");
        }
    }

    /**
     * Rewrites entire csv with the updated Post
     * @param updatedpost data of the updated post
     * @param postid id of the updated post
     */
    @Override
    public void savePostInfo(String[] updatedpost, int postid){
        try {
            Path postsDB = Paths.get(partialpath+"posts.csv");
            // re-reading all post data
            List<String[]> postdata = readAllLines(postsDB);

            // identifying index of the user in the old data
            int i = 1; // indexes from 1 because the first line is a header
            boolean linefound = false;
            while (i < postdata.size() && linefound == false) {
                int id = Integer.parseInt(postdata.get(i)[0]);
                if (id == postid) {
                    linefound = true;
                }
                i += 1;
            }
            postdata.remove(i - 1);
            postdata.add(updatedpost);
            writeAllLines(postsDB, postdata);
        }catch (IOException e1){
            System.out.println("Error occurred while accessing file");
        }catch (CsvException e2){
            System.out.println("Error occurred while writing file");
        }

    }


    /**
     * Writes all the data into a csv file
     * @param path the path of the csv file being written into
     */
    private void writeAllLines(Path path, List<String[]> data) throws IOException, CsvException{
        try (CSVWriter writer = new CSVWriter(new FileWriter(path.toString()))){
            writer.writeAll(data);
        }
    }
}
