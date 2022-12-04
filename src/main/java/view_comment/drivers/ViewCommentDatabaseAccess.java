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
import com.opencsv.CSVWriter;

public class ViewCommentDatabaseAccess implements ViewCommentDsGateway {
    private final String filepath;

    public ViewCommentDatabaseAccess(String filepath) {
        this.filepath = filepath;
    }


    @Override
    public List<String[]> getAllComments(int postId) {
        File file = fileGetter("comments.csv");
        return getStrings(file);
    }

    @Override
    public List<String[]> getCurrentPosts() {
        File file = fileGetter("post.csv");
        return getStrings(file);
    }


    private List<String[]> getStrings(File file) {
        try {
            FileReader fileReader = new FileReader(file);
            CSVReaderBuilder csvReaderBuilder = new CSVReaderBuilder(fileReader);
            CSVReader reader = csvReaderBuilder.build();
            List<String[]> postData = reader.readAll();
            // Remove the row corresponding to the header
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

    private File fileGetter(String fileName){
        String filePath = filepath + fileName;
        return new File(filePath);
    }
}
