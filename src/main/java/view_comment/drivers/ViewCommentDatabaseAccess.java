package view_comment.drivers;

import com.opencsv.exceptions.CsvException;
import view_comment.use_case.ViewCommentDsGateway;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

/**
 * DatabaseAccess class for the View comment use case
 */
public class ViewCommentDatabaseAccess implements ViewCommentDsGateway {
    private final String filepath;

    /**
     * Initializes a ViewCommentDatabaseAccess object
     * @param filepath a String object with the path to all databases (does not include the file
     *                    name of each database)
     */
    public ViewCommentDatabaseAccess(String filepath) {
        this.filepath = filepath;
    }


    /**
     * @return a list of all comments.
     */
    @Override
    public List<String[]> getAllComments() {
        File file = fileGetter("comments.csv");
        return getStrings(file);
    }

    /**
     * @return a list of all posts.
     */
    @Override
    public List<String[]> getAllPosts() {
        File file = fileGetter("posts.csv");
        return getStrings(file);
    }

    /**
     * @return helper method for getAllComments and getAllPosts returning list of lists of string.
     */
    private List<String[]> getStrings(File file) {
        try {
            FileReader fileReader = new FileReader(file);
            CSVReaderBuilder csvReaderBuilder = new CSVReaderBuilder(fileReader);
            CSVReader reader = csvReaderBuilder.build();
            List<String[]> postData = reader.readAll();
            reader.close();
            return postData;


        } catch (FileNotFoundException e) {
            System.out.println("file not found or incorrect format");
            return null;
        } catch (IOException e) {
            System.out.println("Error during accessing file");
            return null;
        } catch (CsvException e) {
            System.out.println("Error during reading file");
            return null;
        }
    }
    /**
     * @return helper method that removes duplicate code when accessing file.
     */
    private File fileGetter(String fileName){
        String filePath = filepath + fileName;
        return new File(filePath);
    }
}