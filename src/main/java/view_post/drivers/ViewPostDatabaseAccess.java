package view_post.drivers;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import com.opencsv.validators.RowMustHaveSameNumberOfColumnsAsFirstRowValidator;
import com.opencsv.validators.RowValidator;
import view_post.use_case.ViewPostDsGateway;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ViewPostDatabaseAccess implements ViewPostDsGateway {
    private final String path;

    /**
     * Initialize a ViewPostDatabaseAccess object
     * @param path The file path of the posts.csv file.
     */
    public ViewPostDatabaseAccess(String path) {
        this.path = path;
    }

    /**
     * Return a list of strings representing the data of the post with matching post IDs.
     */
    public String[] getPostInfo(int postID) {
        String[] postInfo = new String[5];
        File file = new File(path);

        try {
            FileReader fileReader = new FileReader(file);
            CSVReaderBuilder csvReaderBuilder = new CSVReaderBuilder(fileReader);
            RowValidator rowValidator = new RowMustHaveSameNumberOfColumnsAsFirstRowValidator();
            csvReaderBuilder.withRowValidator(rowValidator);

            CSVReader csvReader = csvReaderBuilder.build();
            String[] currLine = csvReader.readNext();
            while (currLine != null) {
                int currPostID = Integer.parseInt(currLine[0]);
                if (currPostID == postID) {
                    postInfo = currLine;
                    break;
                }
                currLine = csvReader.readNext();
            }

        } catch (IOException e) {
            System.out.println("Error during accessing file.");
        } catch (CsvException e) {
            System.out.println("Error during reading file");
        }

        return postInfo;
    }
}
