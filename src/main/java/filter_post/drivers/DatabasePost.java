package filter_post.drivers;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import filter_post.use_case.FilterPostDsGateway;

import com.opencsv.exceptions.CsvException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class DatabasePost implements FilterPostDsGateway {
    private final String path;

    /**
     * Initialize a DatabasePost object.
     * @param path The file path of the posts.csv file.
     */
    public DatabasePost(String path) {
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

            CSVReader reader = new CSVReaderBuilder(fileReader).build();
            List<String[]> postData = reader.readAll();
            // Remove the row corresponding to the header
            postData.remove(0);

            return postData;
        } catch (IOException e) {
            System.out.println("Error during reading:" + e.getMessage());
        } catch (CsvException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
