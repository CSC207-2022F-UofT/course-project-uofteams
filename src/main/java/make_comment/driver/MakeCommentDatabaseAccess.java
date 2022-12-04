package make_comment.driver;

import com.opencsv.exceptions.CsvException;
import make_comment.use_case.MakeCommentGateway;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.*;

public class MakeCommentDatabaseAccess implements MakeCommentGateway {
    private final String filepath;

    public MakeCommentDatabaseAccess(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public int getNumComments(){
        String filePath = filepath + "numCommentsCreated.csv";
        File file = new File(filePath);
        try{
            FileReader fileReader = new FileReader(file);
            CSVReader csvReader = new CSVReaderBuilder(fileReader).withSkipLines(1).build();
            int numCommentsCreated = Integer.parseInt(csvReader.peek()[0]);
            csvReader.close();
            return numCommentsCreated;


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setNumComments(int newNumCommentCreated) {

        File file = fileGetter("numCommentsCreated.csv");

        try {
            FileReader fileReader = new FileReader(file);

            CSVReader csvReader = new CSVReader(fileReader);

            List<String[]> csvBody = csvReader.readAll();
            csvBody.get(1)[0] = String.valueOf(newNumCommentCreated);
            FileWriter outputFile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputFile);
            writer.writeAll(csvBody);
            writer.flush();
            writer.close();
            csvReader.close();
        } catch (IOException | CsvException e) {
            System.out.println("file not found or incorrect format, comment is not saved");
        }
    }

    @Override
    public void saveComment(Map<String, String> commentAttributes) {
        File file = fileGetter("comments.csv");
        String[] commentAtt = new String[commentAttributes.size()];
        commentAtt[0] = commentAttributes.get("commentID");
        commentAtt[1] = commentAttributes.get("commenterID");
        commentAtt[2] = commentAttributes.get("body");
        commentAtt[3] = commentAttributes.get("creationDate");

        try {
            FileReader filereader = new FileReader(file);
            CSVReader csvReader = new CSVReader(filereader);

            List<String[]> csvBody = csvReader.readAll();
            csvBody.add(commentAtt);
            FileWriter outputFile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputFile);
            writer.writeAll(csvBody);
            writer.flush();
            writer.close();
            csvReader.close();
        } catch (IOException | CsvException e) {
          System.out.println("file not found or incorrect format, comment is not saved");
        }

    }

    @Override
    public void updatePostDB(List<String[]> updatedPosts) {
        File file = fileGetter("posts.csv");
        try {
            FileWriter filewriter = new FileWriter(file);
            CSVWriter writer = new CSVWriter(filewriter);
            writer.writeAll(updatedPosts);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("file not found or incorrect format, comment is not saved");
        }


    }


    @Override
    public List<String[]> getCurrentPosts() {

        File file = fileGetter("posts.csv");

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