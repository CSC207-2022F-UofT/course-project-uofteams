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
    public ArrayList<Integer> getFavourites() {
        int userID = CurrentUser.getCurrentUser().getId();

        return null;
    }

    /**
     * Return a list of IDs of the posts the Current User has made
     */
    @Override
    public ArrayList<Integer> getMyPosts() {
        return null;
    }
}
