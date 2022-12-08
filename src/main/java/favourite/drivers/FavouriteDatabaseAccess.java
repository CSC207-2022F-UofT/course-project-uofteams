package favourite.drivers;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import entities.Post;
import entities.User;
import favourite.use_case.FavouriteDsGateway;
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

public class FavouriteDatabaseAccess implements FavouriteDsGateway {
    // postReader is used to allow class DataAccess to access PostFactory to convert data into
    // Post entity in getPost method
    private final PostReaderInterface postReader;
    // userReader is used to allow class DataAccess to access UserFactory to convert data into
    // User entity in getUser method
    private final UserReaderInterface userReader;
    // partialPath is a String that contains the partial directory that leads to all csv files in the program
    // it is isolated from the file name so that in case the file is moved, the code is still compatible
    private final String partialPath;
    /**
     * Initializes DataAccess
     *
     * @param postReader an instance of PostReaderInterface
     * @param userReader an instance of UserReaderInterface
     * @param partialPath the partial path to database files
     */
    public FavouriteDatabaseAccess(PostReaderInterface postReader, UserReaderInterface userReader, String partialPath){
        this.postReader = postReader;
        this.userReader = userReader;
        this.partialPath = partialPath;
    }

    /**
     * Retrieves current user as a User object from the database
     * @param userID the integer ID of the user being retrieved from the database
     * @return the current user as a User object
     */
    @Override
    public User getUser(int userID){
        try {
            // finding and retrieving the current user's data
            List<String[]> allUsers = readAllLines(Paths.get(partialPath+"users.csv"));
            String[] userData = new String[6];
            //i = 1 to skip the header
            for(int i = 1; i < allUsers.size(); i++){
                if(userID == Integer.parseInt(allUsers.get(i)[0])){
                    userData = allUsers.get(i);
                    }
            }
            // turning the current user's data into a user object
            return userReader.readUser(userData);
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
     * @param postID the integer ID of the post being favourited/unfavourited
     * @return a Post object
     */
    @Override
    public Post getPost(int postID){
        try {
            // finding and retrieving the current user's data
            List<String[]> allPosts = readAllLines(Paths.get(partialPath+"posts.csv"));
            String[] postData = new String[10];
            //i = 1 to skip the header
            for(int i = 1; i < allPosts.size(); i++){
                if(postID == Integer.parseInt(allPosts.get(i)[0])){
                    postData = allPosts.get(i);
                }
            }
            return postReader.readPost(postData);
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
     * @param updatedUser a String array of the data of the updated user
     * @param userID the integer ID of the updated user
     */
    @Override
    public void saveUserInfo(String[] updatedUser, int userID) {
        try {
            Path usersDB = Paths.get(partialPath+"users.csv");
            // re-reading all user data
            List<String[]> userData = readAllLines(usersDB);

            // identifying index of the user in the old data
            int i = 1; // indexes from 1 because the first line is a header
            boolean lineFound = false;
            while (i < userData.size() && !lineFound) {
                int id = Integer.parseInt(userData.get(i)[0]);
                if (id == userID) {
                    lineFound = true;
                }
                i += 1;
            }
            userData.remove(i - 1);
            userData.add(updatedUser);
            writeAllLines(usersDB, userData);
        }catch (IOException e1){
            System.out.println("Error occurred while accessing file");
        }catch (CsvException e2){
            System.out.println("Error occurred while reading file");
        }
    }

    /**
     * Rewrites entire csv with the updated Post
     * @param updatedPost a String array of the data of the updated post
     * @param postID the integer ID of the updated post
     */
    @Override
    public void savePostInfo(String[] updatedPost, int postID){
        try {
            Path postsDB = Paths.get(partialPath+"posts.csv");
            // re-reading all post data
            List<String[]> postData = readAllLines(postsDB);

            // identifying index of the user in the old data
            int i = 1; // indexes from 1 because the first line is a header
            boolean lineFound = false;
            while (i < postData.size() && !lineFound) {
                int id = Integer.parseInt(postData.get(i)[0]);
                if (id == postID) {
                    lineFound = true;
                }
                i += 1;
            }
            postData.remove(i - 1);
            postData.add(updatedPost);
            writeAllLines(postsDB, postData);
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
