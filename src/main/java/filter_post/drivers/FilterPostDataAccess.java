package filter_post.drivers;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import com.opencsv.validators.RowMustHaveSameNumberOfColumnsAsFirstRowValidator;
import com.opencsv.validators.RowValidator;
import entities.CurrentUser;
import filter_post.use_case.FilterPostDsGateway;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FilterPostDataAccess implements FilterPostDsGateway {
    // this is the path for posts.csv
    private final String path;

    /**
     * Initialize a FilterPostDataAccess object.
     * @param path The file path of the posts.csv file.
     */
    public FilterPostDataAccess(String path) {
        this.path = path;
    }

    /**
     * Return a list of posts from the repository.
     */
    @Override
    public List<String[]> getPosts() {
        File file = new File(path);

        try {
            FileReader fileReader = new FileReader(file);
            CSVReaderBuilder csvReaderBuilder = new CSVReaderBuilder(fileReader);
            RowValidator rowValidator = new RowMustHaveSameNumberOfColumnsAsFirstRowValidator();
            csvReaderBuilder.withRowValidator(rowValidator);

            CSVReader reader = csvReaderBuilder.build();
            List<String[]> postData = reader.readAll();
            // Remove the row corresponding to the header
            postData.remove(0);

            return postData;
        } catch (IOException e) {
            System.out.println("Error during accessing file.");
        } catch (CsvException e) {
            System.out.println("Error during reading file");
        }
        return new ArrayList<>();
    }

    /**
     * Return a list of IDs of the posts the Current User has favourited
     */
    @Override
    public List<Integer> getFavourites() {
        // getting the ID of the current user
        int userID = CurrentUser.getCurrentUser().getId();

        // adjusting the path so that we don't have to pass another one in the constructor
        String usersPath = path.replace("posts.csv", "users.csv");
        File file = new File(usersPath);

        try {
            // reading everything in the Users database
            FileReader fileReader = new FileReader(file);
            CSVReaderBuilder csvReaderBuilder = new CSVReaderBuilder(fileReader);
            RowValidator rowValidator = new RowMustHaveSameNumberOfColumnsAsFirstRowValidator();
            csvReaderBuilder.withRowValidator(rowValidator);

            CSVReader reader = csvReaderBuilder.build();
            List<String[]> allUserData = reader.readAll();

            // finding the data of the Current User
            String[] currentUserData = new String[6];
            //i = 1 to skip the header
            for(int i = 1; i < allUserData.size(); i++){
                if(userID == Integer.parseInt(allUserData.get(i)[0])){
                    currentUserData = allUserData.get(i);
                }
            }

            // creating a List of Integers of ids of the user's favourited posts from the String
            String[] favIds = currentUserData[5].split(" ");
            List<Integer> favourites = new ArrayList<>();
            for (String ids: favIds){
                if (!ids.isEmpty()){
                    favourites.add(Integer.parseInt(ids));
                }
            }
            return favourites;
        } catch (IOException e) {
            System.out.println("Error during accessing file.");
        } catch (CsvException e) {
            System.out.println("Error during reading file");
        }
        return new ArrayList<>();
    }

    /**
     * Return a list of IDs of the posts the Current User has made
     */
    @Override
    public List<Integer> getMyPosts() {
        // getting the ID of the current user
        int userID = CurrentUser.getCurrentUser().getId();

        // adjusting the path so that we don't have to pass another one in the constructor
        String usersPath = path.replace("posts.csv", "users.csv");
        File file = new File(usersPath);

        try {
            // reading everything in the Users database
            FileReader fileReader = new FileReader(file);
            CSVReaderBuilder csvReaderBuilder = new CSVReaderBuilder(fileReader);
            RowValidator rowValidator = new RowMustHaveSameNumberOfColumnsAsFirstRowValidator();
            csvReaderBuilder.withRowValidator(rowValidator);

            CSVReader reader = csvReaderBuilder.build();
            List<String[]> allUserData = reader.readAll();

            // finding the data of the Current User
            String[] currentUserData = new String[6];
            //i = 1 to skip the header
            for(int i = 1; i < allUserData.size(); i++){
                if(userID == Integer.parseInt(allUserData.get(i)[0])){
                    currentUserData = allUserData.get(i);
                }
            }

            // creating a List of Integers of ids of the user's posts from the String
            String[] myPostIds = currentUserData[4].split(" ");
            List<Integer> myPosts = new ArrayList<>();
            for (String ids: myPostIds){
                if (!ids.isEmpty()){
                    myPosts.add(Integer.parseInt(ids));
                }
            }
            return myPosts;
        } catch (IOException e) {
            System.out.println("Error during accessing file.");
        } catch (CsvException e) {
            System.out.println("Error during reading file");
        }
        return new ArrayList<>();
    }
}
